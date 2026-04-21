package com.example.e_ticketinghelpdeskuts.domain.usecase

import com.example.e_ticketinghelpdeskuts.domain.model.Ticket
import com.example.e_ticketinghelpdeskuts.domain.repository.TicketRepository
import kotlinx.coroutines.flow.Flow

class GetTicketsUseCase(private val repository: TicketRepository) {
    operator fun invoke(): Flow<List<Ticket>> = repository.getTickets()
}
