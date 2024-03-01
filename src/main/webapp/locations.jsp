<%@page import="com.nt.model.Location"%>
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
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

</head>
<body>
	<%List<Location> list=(List<Location>)request.getAttribute("locationList") ;
 
 
 
%>

	<center>

		<h2>
			<a href="<%=request.getContextPath()%>/location?action=new"
				class="btn btn-primary">Add New location</a>


		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<%-- <caption><h2>List of Locations</h2></caption> --%>
			<tr>

				<th>Name</th>
				<th>Code</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="locations" items="${locationList}">
				<tr>

					<td><c:out value="${locations.name}" /></td>
					<td><c:out value="${locations.code}" /></td>

					<td><a
						href="location?action=edit&id=<c:out value='${locations.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="location?action=delete&id=<c:out value='${locations.id}' />">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>