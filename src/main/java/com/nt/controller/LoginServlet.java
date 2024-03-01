package com.nt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.UserDao;
import com.nt.dao.impl.UserDaoImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		
		if (userDao.isValidUser(email, pass)) {
			res.sendRedirect(req.getContextPath() + "/ticket?action=list");
		} else {
			res.sendRedirect("login.jsp?error=1");
		}
	}
	
}
