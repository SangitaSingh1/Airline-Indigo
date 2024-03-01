package com.nt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nt.dao.TicketDao;
import com.nt.dao.impl.TicketDaoImpl;
import com.nt.model.Ticket;
import com.nt.util.CommonUtil;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TicketDao ticketDao;
	private static final Logger logger = LogManager.getLogger(TicketServlet.class);

	public void init() {
		//System.out.println("AddTicketServlet Initialized");
		logger.info("AddTicketServlet Initialized");
		ticketDao = new TicketDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//System.out.println("TicketServlet.doGet()");
		logger.info("TicketServlet.doGet()");

		String action = req.getParameter("action");
		//System.out.println(action);
		logger.debug(action);

		switch (action) {
		case "new":
			showNewForm(req, res);
			break;
		case "insert":
			try {
				insertTicket(req, res);

			} catch (SQLException | IOException | ParseException e) {

				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				deleteTicket(req, res);
			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				showEditForm(req, res);
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			}
			break;
		case "update":
			try {
				update(req, res);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				ticketList(req, res);
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			}
			break;
		}
	}

	public void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//System.out.println("TicketServlet.showNewForm()");
		logger.debug("TicketServlet.showNewForm()");
		RequestDispatcher rd = req.getRequestDispatcher("addTicket.jsp");
		rd.forward(req, res);
	}

	public void insertTicket(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ParseException {

		//System.out.println("TicketServlet.insertTicket()");
		logger.debug("TicketServlet.insertTicket()");
		String departure = req.getParameter("departure");
		String arrival = req.getParameter("arrival");

		String dateInString = req.getParameter("date");

		Date date = CommonUtil.convertStringDateToDate(dateInString);
		System.out.println(date);
		System.out.println(dateInString);
		int seats = Integer.parseInt(req.getParameter("seat"));
		Ticket newTicket = new Ticket(departure, arrival, date, seats, true);

		ticketDao.addTicket(newTicket);
		res.sendRedirect(req.getContextPath() + "/ticket?action=list");
	}

	public void deleteTicket(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		Long tid = Long.parseLong(req.getParameter("id"));
		ticketDao.deleteTicket(tid);
		res.sendRedirect(req.getContextPath() + "/ticket?action=list");
	}

	public void showEditForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		//System.out.println("TicketServlet.showEditForm()");
		logger.debug("TicketServlet.showEditForm()");
		Long tid = Long.parseLong(req.getParameter("id"));
		Ticket existTicket = ticketDao.getById(tid);
		req.setAttribute("ticket", existTicket);
		RequestDispatcher rd = req.getRequestDispatcher("addTicket.jsp");
		rd.forward(req, res);

	}

	public void update(HttpServletRequest req, HttpServletResponse res)
			throws IOException, SQLException, ParseException {
		//System.out.println("TicketServlet.update()");
		logger.debug("TicketServlet.update()");
		Long tid = Long.parseLong(req.getParameter("id"));
		String departure = req.getParameter("departure");
		String arrival = req.getParameter("arrival");
		String dateInString = req.getParameter("date");
		Date date = CommonUtil.convertStringDateToDate(dateInString);
		System.out.println(date);
		System.out.println(dateInString);
		int seats = Integer.parseInt(req.getParameter("seat"));

		Ticket ticket = new Ticket(tid, departure, arrival, date, seats, true);
		ticketDao.updateTicket(ticket);
		res.sendRedirect(req.getContextPath() + "/ticket?action=list");

	}

	public void ticketList(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		System.out.println("TicketServlet.ticketList()");
		List<Ticket> ticketList = ticketDao.getAllTickets();
		System.out.println(ticketList);
		req.setAttribute("ticketList", ticketList);
		RequestDispatcher rd = req.getRequestDispatcher("tickets.jsp");
		rd.forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
