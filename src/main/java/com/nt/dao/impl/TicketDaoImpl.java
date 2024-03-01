package com.nt.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.dao.TicketDao;
import com.nt.model.Ticket;
import com.nt.util.DBUtil;

public class TicketDaoImpl implements TicketDao {

	@Override
	public boolean addTicket(Ticket ticket) {
		System.out.println("TicketDaoImpl.addTicket()");
		boolean rowsAffected = false;
		String query = "INSERT INTO ticket (departure, arrival, date, seats, active) VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {

			pt.setString(1, ticket.getDeparture());
			pt.setString(2, ticket.getArrival());
			pt.setDate(3, new Date(ticket.getDate().getTime()));
			System.out.println(new Date(ticket.getDate().getTime()));
			System.out.println(ticket.getDate());
			pt.setInt(4, ticket.getSeats());
			pt.setBoolean(5, true);

			rowsAffected = pt.executeUpdate()>0;

			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return rowsAffected;

	}

	@Override
	public boolean updateTicket(Ticket ticket) {
		boolean rowUpdated = false;
		String query = "UPDATE ticket SET departure = ?, arrival = ?, date = ?, seats = ?, active = ? WHERE id = ?";

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query);) {

			pt.setString(1, ticket.getDeparture());
			pt.setString(2, ticket.getArrival());
			pt.setDate(3,new Date(ticket.getDate().getTime()) );
			pt.setInt(4, ticket.getSeats());
			pt.setBoolean(5, true);

			pt.setLong(6, ticket.getId());
			rowUpdated = pt.executeUpdate() > 0;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public Ticket getById(Long id) {
		String query = "SELECT * FROM ticket WHERE id = ?";
		Ticket ticket = null;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {
			pt.setLong(1, id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				String daparture = rs.getString("departure");
				String arrival = rs.getString("arrival");
				Date date = rs.getDate("date");

				int seats = rs.getInt("seats");
				Boolean active=rs.getBoolean("active");

				ticket = new Ticket(id, daparture, arrival, date, seats, active);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> getAllTickets() {
		String query = "select * from ticket";
		List<Ticket> tickets = new ArrayList<Ticket>();
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pt = connection.prepareStatement(query)) {
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("id");
				String departure = rs.getString("departure");
				String arrival = rs.getString("arrival");
				Date date = rs.getDate("date");

				int seats = rs.getInt("seats");
				Boolean active=rs.getBoolean("active");
				tickets.add(new Ticket(id, departure, arrival, date, seats, active));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return tickets;
	}

	@Override
	public boolean deleteTicket(Long id) {
		boolean rowdeleted = false;
		String query="DELETE FROM ticket WHERE id = ?";
		try(Connection con=DBUtil.getConnection();PreparedStatement pt=con.prepareStatement(query)){
			pt.setLong(1, id);
			rowdeleted=pt.executeUpdate()>0;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rowdeleted;
	}

}
