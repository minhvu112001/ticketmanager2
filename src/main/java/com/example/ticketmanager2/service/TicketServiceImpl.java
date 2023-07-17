package com.example.ticketmanager2.service;
import com.example.ticketmanager2.dao.TicketRepository;
import com.example.ticketmanager2.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository theTicketRepository){
        ticketRepository = theTicketRepository;
    }

    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Ticket getTicketById(int theId){
        Optional<Ticket> result = ticketRepository.findById(theId);

        Ticket theTicket = null;
        if(result.isPresent()) {
            theTicket = result.get();
        }
        else {
            throw new RuntimeException("Did not find ticket id - " + theId);
        }
        return theTicket;
    }

    @Override
    public void save(Ticket theTicket){
        ticketRepository.save(theTicket);
    }

    @Override
    public void deleteTicket(int theId) {
        ticketRepository.deleteById(theId);
    }

    @Override
   public List<Ticket> searchTicketById(int Id) {
        List<Ticket> results = null;
      Integer idTicket = Integer.valueOf(Id);
      if (idTicket != null) {
           Id = idTicket;
            results = ticketRepository.findTicketById(Id);
        }
        else {
            results = getAllTicket();
        }

       return results;
    }
}
