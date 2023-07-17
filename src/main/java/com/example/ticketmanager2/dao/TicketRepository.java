package com.example.ticketmanager2.dao;
import com.example.ticketmanager2.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findAllByOrderByIdAsc();

    public List<Ticket> findTicketById(int id);
}
