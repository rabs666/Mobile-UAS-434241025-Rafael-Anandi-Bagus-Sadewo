package com.example.e_ticketinghelpdeskuts.domain.model

data class AppUser(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole
)

enum class UserRole {
    USER,
    HELPDESK,
    ADMIN
}

data class Ticket(
    val id: String,
    val title: String,
    val description: String,
    val status: TicketStatus,
    val createdAt: String,
    val applicantId: String,
    val applicant: String,
    val attachmentSource: AttachmentSource = AttachmentSource.NONE,
    val attachmentName: String? = null,
    val assignedTo: String? = null,
    val comments: List<Comment> = emptyList(),
    val activities: List<TicketActivity> = emptyList()
)

data class Comment(
    val id: String,
    val sender: String,
    val message: String,
    val timestamp: String
)

data class TicketActivity(
    val id: String,
    val title: String,
    val actor: String,
    val timestamp: String
)

enum class AttachmentSource {
    NONE,
    CAMERA,
    FILE
}

enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    CLOSED
}
