package com.nt.dao;

import java.util.List;

import com.nt.model.Ticket;

public interface TicketDao {
    
	boolean addTicket(Ticket ticket);
	public boolean updateTicket(Ticket ticket);
	public Ticket getById(Long id);
	public List<Ticket> getAllTickets();
	public boolean deleteTicket(Long id);
	
	
	
	
}
