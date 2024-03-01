<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="ticket.css">
 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="addTicket.css">
</head>
<body>

	<%-- <h2>
		<a href="<%=request.getContextPath()%>/ticket?action=new">Add New
			Ticket</a> &nbsp;&nbsp;&nbsp; <a
			href="<%=request.getContextPath()%>/ticket?action=list">Tickets</a>

	</h2> --%>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${ticket != null}">
					<form action="ticket?action=update" method="post"
						onsubmit="return validateForm();">
				</c:if>
				<c:if test="${ticket == null}">
					<form action="ticket?action=insert" method="post"
						onsubmit="return validateForm();">
				</c:if>

				<caption>
					<h2>
						<c:if test="${ticket != null}">
                                    Edit Ticket
                                </c:if>
						<c:if test="${ticket == null}">
                                    Add New Ticket
                                </c:if>
					</h2>
				</caption>

				<c:if test="${ticket != null}">
					<input type="hidden" name="id"
						value="<c:out value='${ticket.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label for="departure">Departure:</label> <select id="departure"
						name="departure">
						<option value="">Select city</option>

						<!-- Add more options as needed -->
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label for="arrival">Arrival:</label> <select id="arrival"
						name="arrival">
						<option value="">Select city</option>

						<!-- Add more options as needed -->
					</select>
				</fieldset>


				<fieldset class="form-group">
					<label>Date</label> <input type="date"
						value="<c:out value='${ticket.date}' />" class="form-control"
						name="date" id="date" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Seats</label> <input type="number"
						value='${ticket.seats!=null?ticket.seats:120 }'
						class="form-control" name="seat" required="required" min="1"
						max="120">
				</fieldset>



				<button type="submit" class="btn btn-primary">
					<c:choose>
						<c:when test="${ticket != null}">Edit</c:when>
						<c:otherwise>Save</c:otherwise>
					</c:choose>
				</button>
				<button type="reset" class="btn btn-secondary ml-auto cancel-button"
					onclick="cancelAction()">Cancel</button>
				</form>

			</div>
		</div>
	</div>


	<script>
    $(document).ready(function() {
    // AJAX request to fetch data from the servlet
    $.ajax({
        url: '/Airline-indigo/location?action=listForTicket',
        method: 'GET',
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            console.log("success");
            console.log(JSON.stringify(data));

            // Append options to the select box
            for (var i = 0; i < data.length; i++) {
                var option = data[i];
                console.log(option);

                
                $('#departure,#arrival').append('<option value="' + option.code + '">' + option.name + '</option>');

                
                if (${ticket != null}) {
                    if (option.code === '${ticket.departure}') {
                        $('#departure').val('${ticket.departure}');
                    }
                    if (option.code === '${ticket.arrival}') {
                        $('#arrival').val('${ticket.arrival}');
                    }
                }
            }
        },
        error: function(error) {
            console.error('Error fetching data:', error);
        }
    });
    $('input[name="seat"]').on('input', function() {
        updateAvailableSeats();
    });
    updateAvailableSeats();
});
    function updateAvailableSeats() {
        
        var totalSeats = 120; 

        
        var selectedSeats = parseInt($('input[name="seat"]').val()) || 0;

        
        var availableSeats = totalSeats - selectedSeats;

        
        $('input[name="seat"]').attr('placeholder', 'Available Seats: ' + availableSeats);
        $('input[name="seat"]').attr('max', availableSeats);
    }
   
    function validateForm() {
        var departure = $('#departure').val();
        var arrival = $('#arrival').val();
        var currentDate = new Date();
        console.log(currentDate);
       
        
        if (departure === arrival) {
            alert('Departure and Arrival must be different');
            return false; 
        }
        var selectedDate = new Date($('#date').val());
        console.log(selectedDate);
       // selectedDate.setMonth(selectedDate.getMonth() + 1);
        if (selectedDate <= currentDate) {
            alert('Selected date cannot be in the past');
            return false;
        }
        

        
        
        return true; 
    }

 
 function cancelAction() {
    window.location.href = '<%=request.getContextPath()%>/ticket?action=list';
}
 document.addEventListener('DOMContentLoaded', function () {
     var seatInput = document.getElementById('seatInput');

     seatInput.addEventListener('input', function () {
         validateSeatInput();
     });

     function validateSeatInput() {
         var inputValue = parseInt(seatInput.value);

         if (isNaN(inputValue) || inputValue < 1 || inputValue > 120) {
             seatInput.setCustomValidity('Please enter a valid number between 1 and 120.');
         } else {
             seatInput.setCustomValidity('');
         }
     }
 });
</script>

</body>

</html>