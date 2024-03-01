<%@page import="com.nt.model.Ticket"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="sidebar.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ticket list page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<% List<Ticket> list=(List<Ticket>) request.getAttribute("ticketList");
 %>
	<div>
		<center>
			<!-- <h1>Ticket Management</h1> -->
			<h2>

				<a href="<%=request.getContextPath()%>/ticket?action=new"
					class="btn btn-primary">Add new ticket</a>



			</h2>
		</center>
		<div align="center">
			<table border="1" cellpadding="5">
				<%-- <caption><h2>List of Tickets</h2></caption> --%>
				<tr>

					<th>Departure</th>
					<th>Arrival</th>
					<th>Date</th>
					<th>Seats</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="ticket" items="${ticketList}">
					<tr>

						<td><c:out value="${ticket.departure}" /></td>
						<td><c:out value="${ticket.arrival}" /></td>
						<td><c:out value="${ticket.date}" /></td>
						<td><c:out value="${ticket.seats}" /></td>
						<td><a
							href="ticket?action=edit&id=<c:out value='${ticket.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="ticket?action=delete&id=<c:out value='${ticket.id}' />">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>