package com.example.e_ticketinghelpdeskuts.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object ResetPassword : Screen("reset_password")
    object Dashboard : Screen("dashboard")
    object TicketList : Screen("ticket_list")
    object TicketDetail : Screen("ticket_detail/{ticketId}") {
        fun createRoute(ticketId: String) = "ticket_detail/$ticketId"
    }
    object CreateTicket : Screen("create_ticket")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")
    object UserManagement : Screen("user_management")
}
