<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="sidebar.css">
</head>
<body>
<div id="sidebar">
    <nav>
        <ul>
            <h2>Admin Dashboard</h2>
            <li><a href="<%=request.getContextPath()%>/ticket?action=ticket">Tickets</a></li>
            <li><a href="<%=request.getContextPath()%>/location?action=list">Locations</a></li>
            
            
        </ul>
        </nav>
        </div>
</body>
</html>