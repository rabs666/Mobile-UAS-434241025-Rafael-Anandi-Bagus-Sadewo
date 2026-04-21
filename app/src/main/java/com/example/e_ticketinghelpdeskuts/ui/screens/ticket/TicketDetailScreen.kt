package com.example.e_ticketinghelpdeskuts.ui.screens.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticketinghelpdeskuts.domain.model.AttachmentSource
import com.example.e_ticketinghelpdeskuts.domain.model.Ticket
import com.example.e_ticketinghelpdeskuts.domain.model.TicketActivity
import com.example.e_ticketinghelpdeskuts.domain.model.TicketStatus
import com.example.e_ticketinghelpdeskuts.domain.model.UserRole

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(navController: NavController, viewModel: TicketViewModel, ticketId: String?) {
    val ticket by if (ticketId != null) {
        viewModel.getTicketDetail(ticketId).collectAsState()
    } else {
        remember { mutableStateOf(null) }
    }

    val currentUser by viewModel.currentUser.collectAsState()
    val assignableAgents by viewModel.assignableAgents.collectAsState()
    var replyText by remember { mutableStateOf("") }
    var selectedAssignee by remember { mutableStateOf("") }
    val canManage = currentUser?.role == UserRole.ADMIN || currentUser?.role == UserRole.HELPDESK

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Tiket #${ticketId ?: ""}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (ticket == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Tiket tidak ditemukan atau akses ditolak.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    TicketInfoCard(ticket = ticket!!)

                    if (canManage) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Manajemen Tiket", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TicketStatus.values().forEach { status ->
                                FilterChip(
                                    selected = ticket!!.status == status,
                                    onClick = { viewModel.updateStatus(ticket!!.id, status) },
                                    label = { Text(status.name) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            assignableAgents.forEach { agent ->
                                SuggestionChip(
                                    onClick = { selectedAssignee = agent },
                                    label = { Text(agent) }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = selectedAssignee,
                            onValueChange = { selectedAssignee = it },
                            label = { Text("Assign ke petugas") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                if (selectedAssignee.isNotBlank()) {
                                    viewModel.assignTicket(ticket!!.id, selectedAssignee)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = selectedAssignee.isNotBlank()
                        ) {
                            Text("Assign Tiket")
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Tracking Tiket", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                itemsIndexed(ticket!!.activities) { index, activity ->
                    TrackingItem(
                        activity = activity,
                        isLast = index == ticket!!.activities.lastIndex
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Percakapan", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(ticket!!.comments) { comment ->
                    ChatBubble(
                        sender = comment.sender,
                        message = comment.message,
                        time = comment.timestamp,
                        isCurrentUser = comment.sender == currentUser?.name
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = replyText,
                        onValueChange = { replyText = it },
                        label = { Text("Balas...") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            viewModel.addComment(ticket!!.id, replyText)
                            replyText = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = replyText.isNotBlank()
                    ) {
                        Text("Kirim")
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
private fun TicketInfoCard(ticket: Ticket) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = ticket.title, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Status:", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(8.dp))
                TicketStatusPill(status = ticket.status)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Deskripsi: ${ticket.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Dibuat oleh: ${ticket.applicant}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Waktu: ${ticket.createdAt}", style = MaterialTheme.typography.bodySmall)

            if (ticket.attachmentSource != AttachmentSource.NONE) {
                Text(
                    text = "Lampiran: ${ticket.attachmentSource.name}${ticket.attachmentName?.let { " - $it" } ?: ""}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = "Assigned: ${ticket.assignedTo ?: "Belum ditugaskan"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun TrackingItem(activity: TicketActivity, isLast: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.White)
            }

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(20.dp)
                        .background(Color.Gray)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = activity.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text(text = "${activity.actor} • ${activity.timestamp}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun TicketStatusPill(status: TicketStatus) {
    val color = when (status) {
        TicketStatus.OPEN -> Color(0xFFE57373)
        TicketStatus.IN_PROGRESS -> Color(0xFF64B5F6)
        TicketStatus.CLOSED -> Color(0xFF81C784)
    }

    Surface(
        color = color,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun ChatBubble(sender: String, message: String, time: String, isCurrentUser: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (isCurrentUser) Alignment.End else Alignment.Start
    ) {
        Surface(
            color = if (isCurrentUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = sender, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
                Text(text = message)
                Text(text = time, style = MaterialTheme.typography.labelSmall, modifier = Modifier.align(Alignment.End))
            }
        }
    }
}
