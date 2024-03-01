<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="register.css">
</head>
<body>
	<div class="container">
		<form id="registrationForm" action="register" method="post">
			<label for="firstName">First Name:</label> <input type="text"
				id="firstName" name="firstName" required> <label
				for="lastName">Last Name:</label> <input type="text" id="lastName"
				name="lastName" required> <label for="email">Email:</label>
			<input type="email" id="email" name="email" required> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required>

			<button type="submit">Register</button>

			<p>
				Already have an account? <a href="login.jsp">Login</a>
			</p>
		</form>
		<%
		String error = request.getParameter("error");
		if (error != null && error.equals("1")) {
		%>
		<p style="color: red;">Registration failed. Please try again.</p>
		<%
		}
		%>
	</div>
</body>
</html>