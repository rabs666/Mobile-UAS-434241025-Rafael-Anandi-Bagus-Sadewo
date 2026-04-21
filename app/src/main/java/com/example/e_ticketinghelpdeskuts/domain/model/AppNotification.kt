package com.example.e_ticketinghelpdeskuts.domain.model

data class AppNotification(
    val id: String,
    val title: String,
    val message: String,
    val ticketId: String?,
    val timestamp: String,
    val isRead: Boolean = false
)
