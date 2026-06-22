package com.example.e_ticketinghelpdeskuts.utils

import android.util.Log

object DebugUtils {
    private const val TAG = "ETicketingHelpdesk"

    fun logError(message: String, exception: Exception? = null) {
        Log.e(TAG, message, exception)
    }

    fun logDebug(message: String) {
        Log.d(TAG, message)
    }

    fun logInfo(message: String) {
        Log.i(TAG, message)
    }

    fun logWarning(message: String) {
        Log.w(TAG, message)
    }
}
