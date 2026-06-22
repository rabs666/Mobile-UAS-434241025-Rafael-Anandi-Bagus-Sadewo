package com.example.e_ticketinghelpdeskuts.ui.utils

/**
 * Utility object for input validation
 */
object InputValidation {
    
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && email.contains("@") && email.contains(".")
    }
    
    fun isValidUsername(username: String): Boolean {
        return username.isNotBlank() && username.length >= 3
    }
    
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
    
    fun isValidTicketTitle(title: String): Boolean {
        return title.isNotBlank() && title.length >= 5 && title.length <= 100
    }
    
    fun isValidTicketDescription(description: String): Boolean {
        return description.isNotBlank() && description.length >= 10 && description.length <= 1000
    }
    
    fun getTicketTitleError(title: String): String? {
        return when {
            title.isBlank() -> "Judul tidak boleh kosong"
            title.length < 5 -> "Judul minimal 5 karakter"
            title.length > 100 -> "Judul maksimal 100 karakter"
            else -> null
        }
    }
    
    fun getTicketDescriptionError(description: String): String? {
        return when {
            description.isBlank() -> "Deskripsi tidak boleh kosong"
            description.length < 10 -> "Deskripsi minimal 10 karakter"
            description.length > 1000 -> "Deskripsi maksimal 1000 karakter"
            else -> null
        }
    }
    
    fun getEmailError(email: String): String? {
        return when {
            email.isBlank() -> "Email tidak boleh kosong"
            !isValidEmail(email) -> "Format email tidak valid"
            else -> null
        }
    }
    
    fun getPasswordError(password: String): String? {
        return when {
            password.isBlank() -> "Password tidak boleh kosong"
            password.length < 6 -> "Password minimal 6 karakter"
            else -> null
        }
    }
}
