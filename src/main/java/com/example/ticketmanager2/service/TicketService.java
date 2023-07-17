package com.example.ticketmanager2.service;
import com.example.ticketmanager2.entity.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> getAllTicket();
    public Ticket getTicketById(int theId);
    public void save(Ticket theTicket);
    public void deleteTicket(int theId);

    List<Ticket> searchTicketById(int theId);
//    public List<Ticket> searchTicketById(int theId);
}