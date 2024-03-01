package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nt.dao.LocationDao;
import com.nt.dao.impl.LocationDaoImpl;
import com.nt.model.Location;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {

	LocationDao locationDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		System.out.println("AddLocationServlet Initialized");
		locationDao = new LocationDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("LocationServlet.doGet()");
		String action = req.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "new":
			System.out.println("LocationServlet.doGet()");
			showNewForm(req, res);
			break;
		case "insert":
			System.out.println("LocationServlet.doGet() insert");
			try {
				insertLocation(req, res);
			} catch (SQLException | IOException | ParseException e) {

				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				deleteLocation(req, res);
			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				showEditForm(req, res);
				System.out.println("LocationServlet.doGet() edit");
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			}
			break;
		case "update":
			try {
				update(req, res);
			} catch (IOException | SQLException e) {

				e.printStackTrace();
			} catch (ParseException e) {

				e.printStackTrace();
			}
			break;
		case "listForTicket":
			try {
				listForTicket(req, res);
			} catch (IOException | SQLException e) {

				e.printStackTrace();
			}
			break;

		default:
			try {
				locationList(req, res);
				System.out.println("LocationServlet.doGet()");
			} catch (SQLException | ServletException | IOException e) {

				e.printStackTrace();
			}
			break;
		}
	}

	public void showNewForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("LocationServlet.showNewForm()");
		RequestDispatcher rd = req.getRequestDispatcher("addLocation.jsp");
		rd.forward(req, res);
	}

	public void insertLocation(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ParseException, ServletException {

		/*
		 * System.out.println("LocationServlet.insertLocation()"); String name =
		 * req.getParameter("name"); String code = req.getParameter("code");
		 * if(locationDao.isLocationExists(name, code)) { req.setAttribute("error",
		 * "Location name already exists. Please choose a different name.");
		 * RequestDispatcher rd = req.getRequestDispatcher("addLocation.jsp");
		 * rd.forward(req, res); return; }else { Location newLocation = new
		 * Location(name, code, true); System.out.println(newLocation);
		 * locationDao.addLocation(newLocation);
		 * System.out.println(req.getContextPath());
		 * res.sendRedirect(req.getContextPath() + "/location?action=list"); }
		 */
		
		System.out.println("LocationServlet.insertLocation()");
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		System.out.println("Name: " + name + ", Code: " + code);

		if (locationDao.isLocationExists(name)) {
		    System.out.println("Duplicate location found");
		    req.setAttribute("error", "Location name already exists. Please choose a different name.");
		    RequestDispatcher rd = req.getRequestDispatcher("addLocation.jsp");
		    rd.forward(req, res);
		    return;
		} else {
		    Location newLocation = new Location(name, code, true);
		    System.out.println("New Location: " + newLocation);
		    locationDao.addLocation(newLocation);
		    System.out.println("Location added successfully");
		    res.sendRedirect(req.getContextPath() + "/location?action=list");
		}
		

	}

	public void deleteLocation(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		Long lid = Long.parseLong(req.getParameter("id"));
		locationDao.deleteLocation(lid);
		res.sendRedirect(req.getContextPath() + "/location?action=list");

	}

	public void showEditForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		System.out.println("LocationServlet.showEditForm()");
		Long lid = Long.parseLong(req.getParameter("id"));
		Location existLocation = locationDao.getById(lid);
		System.out.println(existLocation);
		System.out.println("LocationServlet.showEditForm()");
		req.setAttribute("location", existLocation);
		RequestDispatcher rd = req.getRequestDispatcher("addLocation.jsp");
		rd.forward(req, res);

	}

	public void update(HttpServletRequest req, HttpServletResponse res)
			throws IOException, SQLException, ParseException {
		Long lid = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String code = req.getParameter("code");

		Location location = new Location(lid, name, code, true);
		locationDao.updateLocation(location);
		res.sendRedirect(req.getContextPath() + "/location?action=list");

	}

	public void locationList(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		System.out.println("LocationServlet.locationList()");
		List<Location> locationList = locationDao.getAllLocations();
		System.out.println(locationList);
		req.setAttribute("locationList", locationList);
		RequestDispatcher rd = req.getRequestDispatcher("locations.jsp");
		rd.forward(req, res);

	}

	public void listForTicket(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, ServletException, IOException {
		System.out.println("LocationServlet.listForTicket()");
		List<Location> locationList = locationDao.getAllLocations();
		System.out.println(locationList);

		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		String result = gson.toJson(locationList);
		out.println(result);
		out.flush();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}

}
