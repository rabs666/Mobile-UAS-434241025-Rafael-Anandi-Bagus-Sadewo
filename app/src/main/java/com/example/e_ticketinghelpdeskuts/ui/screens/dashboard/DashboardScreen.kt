package com.example.e_ticketinghelpdeskuts.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticketinghelpdeskuts.domain.model.TicketStatus
import com.example.e_ticketinghelpdeskuts.ui.navigation.Screen
import com.example.e_ticketinghelpdeskuts.ui.screens.ticket.TicketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController, viewModel: TicketViewModel) {
    val tickets by viewModel.tickets.collectAsState()
    val unreadCount by viewModel.unreadNotificationCount.collectAsState()
    
    // Hitung statistik secara real-time
    val totalCount = tickets.size
    val openCount = tickets.count { it.status == TicketStatus.OPEN }
    val progressCount = tickets.count { it.status == TicketStatus.IN_PROGRESS }
    val closedCount = tickets.count { it.status == TicketStatus.CLOSED }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("E-Helpdesk") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Notifications.route) }) {
                        BadgedBox(badge = { if (unreadCount > 0) Badge { Text(unreadCount.toString()) } }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.CreateTicket.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Create Ticket")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Ringkasan Tiket", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { StatCard("Total Tiket", totalCount.toString(), Icons.AutoMirrored.Filled.List, MaterialTheme.colorScheme.primary) }
                item { StatCard("Open", openCount.toString(), Icons.Default.Info, MaterialTheme.colorScheme.error) }
                item { StatCard("In Progress", progressCount.toString(), Icons.Default.Build, MaterialTheme.colorScheme.secondary) }
                item { StatCard("Selesai", closedCount.toString(), Icons.Default.CheckCircle, MaterialTheme.colorScheme.tertiary) }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(text = "Aksi Cepat", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedButton(
                onClick = { navController.navigate(Screen.TicketList.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lihat Riwayat Tiket")
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, icon: ImageVector, color: androidx.compose.ui.graphics.Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = color)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.headlineMedium, color = color)
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
