<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="addLocation.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script type="text/javascript">
	function cancelAction() {
        
        window.location.href = '<%=request.getContextPath()%>/location?action=list';
    }
	function validateForm() {
	    var locationNameInput = document.getElementById("name");
	    var locationName = locationNameInput.value.trim();

	    // Check for duplicate names on the client side
	    var existingNames = document.querySelectorAll('.existing-names');
	    for (var i = 0; i < existingNames.length; i++) {
	        if (existingNames[i].innerText === locationName) {
	            alert("Location with this name already exists.");
	            locationNameInput.focus();
	            return false;
	        }
	    }

	    // Additional validation logic can be added as needed.

	    return true;
	}
	</script>
</head>
<body>

	<% if (request.getAttribute("error") != null) { %>
	<div class="error-message" style="color: red;">
		<%= request.getAttribute("error") %>
	</div>
	<% } %>

	<%-- <h2>
		<a href="<%=request.getContextPath()%>/location?action=new">Add
			New Location</a> &nbsp;&nbsp;&nbsp; <a
			href="<%=request.getContextPath()%>/location?action=list">Locations</a>

	</h2>
 --%>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${location != null}">
					<form action="location?action=update" method="post">
				</c:if>
				<c:if test="${location == null}">
					<form action="location?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${location != null}">
                                    Edit Location
                                </c:if>
						<c:if test="${location == null}">
                                    Add New Location
                                </c:if>
						<%-- <c:if test="${not empty requestScope.error}">
							<div style="color: red;">${requestScope.error}</div>
						</c:if> --%>
					</h2>
				</caption>

				<c:if test="${location != null}">
					<input type="hidden" name="id"
						value="<c:out value='${location.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Location Name</label> <input type="text"
						value="<c:out value='${location.name}' />" class="form-control"
						name="name" required="required" id="name">
				</fieldset>

				<fieldset class="form-group">
					<label>Location Code</label> <input type="text"
						value="<c:out value='${location.code}' />" class="form-control"
						name="code">
				</fieldset>



				<button type="submit" class="btn btn-primary">
					<c:choose>
						<c:when test="${location != null}">Edit</c:when>
						<c:otherwise>Save</c:otherwise>
					</c:choose>
				</button>
				<button type="reset" class="btn btn-secondary ml-auto cancel-button"
					onclick="cancelAction()">Cancel</button>
				</form>


			</div>
		</div>
	</div>



</body>
</html>