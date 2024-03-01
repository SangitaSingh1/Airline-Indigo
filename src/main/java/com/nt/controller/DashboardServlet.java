package com.nt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.LocationDao;
import com.nt.dao.TicketDao;
import com.nt.dao.impl.LocationDaoImpl;
import com.nt.dao.impl.TicketDaoImpl;
import com.nt.model.Location;
import com.nt.model.Ticket;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{
	private TicketDao ticketDao;
	private LocationDao locationDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		ticketDao=new TicketDaoImpl();
		locationDao=new LocationDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			/*
			 * String action = req.getParameter("action"); switch (action) { case "ticket":
			 * showTicket(req, res); break; default:
			 * 
			 * try { showLocation(req, res); } catch (ServletException | IOException e) {
			 * 
			 * e.printStackTrace(); } break;
			 * 
			 * }
			 * 
			 */
		
    

		
	
	  if(req.getParameter("ticket") != null) { List<Ticket> ticketList =
	  ticketDao.getAllTickets(); System.out.println(ticketList);
	  req.setAttribute("ticketList", ticketList); RequestDispatcher rd =
	  req.getRequestDispatcher("tickets.jsp"); rd.forward(req, res);
	  
	  }else if (req.getParameter("location") != null) { List<Location> locationList
	  = locationDao.getAllLocations(); System.out.println(locationList);
	  req.setAttribute("locationList", locationList); RequestDispatcher rd =
	  req.getRequestDispatcher("locations.jsp"); rd.forward(req, res); }
	  
	}
		
		 
	/*
	 * public void showTicket(HttpServletRequest req, HttpServletResponse res)
	 * throws ServletException, IOException { List<Ticket> ticketList =
	 * ticketDao.getAllTickets(); System.out.println(ticketList);
	 * req.setAttribute("ticketList", ticketList); RequestDispatcher rd =
	 * req.getRequestDispatcher("tickets.jsp"); rd.forward(req, res); } public void
	 * showLocation(HttpServletRequest req, HttpServletResponse res) throws
	 * ServletException, IOException {
	 * 
	 * List<Location> locationList = locationDao.getAllLocations();
	 * System.out.println(locationList); req.setAttribute("locationList",
	 * locationList); RequestDispatcher rd =
	 * req.getRequestDispatcher("locations.jsp"); rd.forward(req, res); }
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
