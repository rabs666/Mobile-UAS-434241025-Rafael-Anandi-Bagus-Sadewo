package com.example.e_ticketinghelpdeskuts.ui.screens.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticketinghelpdeskuts.ui.navigation.Screen
import com.example.e_ticketinghelpdeskuts.ui.screens.ticket.TicketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController, viewModel: TicketViewModel) {
    val notifications by viewModel.notifications.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifikasi") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (notifications.isNotEmpty()) {
                        TextButton(onClick = { viewModel.markAllNotificationsAsRead() }) {
                            Text("Tandai Dibaca")
                        }
                    }
                }
            )
        }
    ) { padding ->
        if (notifications.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(text = "Belum ada notifikasi.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(notifications) { notification ->
                    ListItem(
                        headlineContent = { Text(notification.title) },
                        supportingContent = { Text(notification.message) },
                        overlineContent = { Text(notification.timestamp) },
                        leadingContent = {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = null,
                                tint = if (notification.isRead) Color.Gray else MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingContent = {
                            if (!notification.isRead) {
                                Badge()
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .clickable {
                                notification.ticketId?.let { ticketId ->
                                    navController.navigate(Screen.TicketDetail.createRoute(ticketId))
                                }
                            }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
