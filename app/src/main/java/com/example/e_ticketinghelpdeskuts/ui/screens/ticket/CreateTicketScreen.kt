package com.example.e_ticketinghelpdeskuts.ui.screens.ticket

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticketinghelpdeskuts.domain.model.AttachmentSource
import com.example.e_ticketinghelpdeskuts.domain.model.UserRole
import com.example.e_ticketinghelpdeskuts.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketScreen(navController: NavController, viewModel: TicketViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var attachmentSource by remember { mutableStateOf(AttachmentSource.NONE) }
    var attachmentName by remember { mutableStateOf("") }
    val currentUser by viewModel.currentUser.collectAsState()
    val authMessage by viewModel.authMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buat Tiket") },
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
                .padding(16.dp)
        ) {
            if (currentUser?.role != UserRole.USER) {
                Text(
                    text = "Hanya user pelapor yang dapat membuat tiket baru.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Kembali")
                }
                return@Column
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Laporan") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Masalah") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Sumber Lampiran", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = attachmentSource == AttachmentSource.NONE,
                    onClick = { attachmentSource = AttachmentSource.NONE },
                    label = { Text("Tanpa") }
                )
                FilterChip(
                    selected = attachmentSource == AttachmentSource.CAMERA,
                    onClick = { attachmentSource = AttachmentSource.CAMERA },
                    label = { Text("Kamera") }
                )
                FilterChip(
                    selected = attachmentSource == AttachmentSource.FILE,
                    onClick = { attachmentSource = AttachmentSource.FILE },
                    label = { Text("File") }
                )
            }

            if (attachmentSource != AttachmentSource.NONE) {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = attachmentName,
                    onValueChange = { attachmentName = it },
                    label = {
                        if (attachmentSource == AttachmentSource.CAMERA) {
                            Text("Nama Foto (simulasi kamera)")
                        } else {
                            Text("Nama File Lampiran")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (attachmentSource != AttachmentSource.NONE && attachmentName.isBlank()) {
                        return@Button
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    when (attachmentSource) {
                        AttachmentSource.CAMERA -> "Simulasi Ambil Foto"
                        AttachmentSource.FILE -> "Simulasi Upload File"
                        AttachmentSource.NONE -> "Pilih Sumber Lampiran"
                    }
                )
            }

            authMessage?.let { message ->
                Spacer(modifier = Modifier.height(12.dp))
                AssistChip(onClick = {}, label = { Text(message) })
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        viewModel.createTicket(
                            title = title,
                            description = description,
                            attachmentSource = attachmentSource,
                            attachmentName = attachmentName.ifBlank { null }
                        )
                        navController.navigate(Screen.TicketList.route) {
                            popUpTo(Screen.Dashboard.route)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() && description.isNotBlank()
            ) {
                Text("Simpan Tiket")
            }
        }
    }
}
