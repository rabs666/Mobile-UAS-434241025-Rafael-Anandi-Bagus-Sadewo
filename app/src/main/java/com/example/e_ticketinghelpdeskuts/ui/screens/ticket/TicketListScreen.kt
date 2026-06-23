package com.example.e_ticketinghelpdeskuts.ui.screens.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticketinghelpdeskuts.domain.model.Ticket
import com.example.e_ticketinghelpdeskuts.domain.model.TicketStatus
import com.example.e_ticketinghelpdeskuts.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(navController: NavController, viewModel: TicketViewModel) {
    val tickets by viewModel.tickets.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()
    val selectedStatus by viewModel.selectedStatusFilter.collectAsState()

    val filteredTickets = remember(tickets, selectedStatus) {
        selectedStatus?.let { status -> tickets.filter { it.status == status } } ?: tickets
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val roleText = currentUser?.let { viewModel.roleLabel(it.role) } ?: "Guest"
                    Text("Daftar Tiket - $roleText")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedStatus == null,
                    onClick = { viewModel.selectStatusFilter(null) },
                    label = { Text("Semua") }
                )
                FilterChip(
                    selected = selectedStatus == TicketStatus.OPEN,
                    onClick = { viewModel.selectStatusFilter(TicketStatus.OPEN) },
                    label = { Text("Open") }
                )
                FilterChip(
                    selected = selectedStatus == TicketStatus.IN_PROGRESS,
                    onClick = { viewModel.selectStatusFilter(TicketStatus.IN_PROGRESS) },
                    label = { Text("In Progress") }
                )
                FilterChip(
                    selected = selectedStatus == TicketStatus.CLOSED,
                    onClick = { viewModel.selectStatusFilter(TicketStatus.CLOSED) },
                    label = { Text("Closed") }
                )
            }

            if (filteredTickets.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text("Belum ada tiket pada filter ini.")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredTickets) { ticket ->
                        TicketListItem(ticket = ticket) {
                            navController.navigate(Screen.TicketDetail.createRoute(ticket.id))
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun TicketListItem(ticket: Ticket, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text(ticket.title) },
        supportingContent = {
            Text(
                "${ticket.createdAt} • ${ticket.id}\nPelapor: ${ticket.applicant}" +
                    (ticket.assignedTo?.let { "\nAssigned: $it" } ?: "")
            )
        },
        trailingContent = {
            Surface(
                color = when (ticket.status) {
                    TicketStatus.OPEN -> Color(0xFFE57373)
                    TicketStatus.IN_PROGRESS -> Color(0xFF64B5F6)
                    TicketStatus.CLOSED -> Color(0xFF81C784)
                },
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = ticket.status.name,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    )
}
