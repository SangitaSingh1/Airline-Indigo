<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="dashboard.css">

    <!-- <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        #sidebar {
            width: 250px;
            height: 100%;
            position: fixed;
            background: #333;
            left: 0;
            overflow-x: hidden;
            padding-top: 20px;
        }

        #content {
            margin-left: 250px;
            padding: 16px;
        }

        #content h2 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: white;
        }
    </style>
 --></head>
<body>

    <div id="sidebar">
        <!-- <h2>Admin Dashboard</h2>
        <form action="dashboard" method="get">
        <input type="submit" value="ticket" name="ticket">
        <input type="submit" value="location" name="location">
        </form> -->
        <nav>
        <ul>
            <!-- <li><a href="locations.jsp">Location</a></li>
            <li><a href="tickets.jsp">Ticket</a></li> -->
            <li><a href="<%=request.getContextPath()%>/ticket?action=ticket">Tickets</a></li>
            <li><a href="<%=request.getContextPath()%>/location?action=list">Locations</a></li>
            
            
        </ul>
        </nav>
    </div>
      
    
</body>
</html>
