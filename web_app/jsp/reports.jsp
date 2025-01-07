<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reports</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
  <img src="images/fuel-logo.png" alt="Fuel Management Logo" class="logo">
  <h1>Reports</h1>
  <nav>
    <ul>
      <li><a href="index.jsp">Home</a></li>
      <li><a href="fuel-records.jsp">Fuel Records</a></li>
      <li><a href="settings.jsp">Settings</a></li>
    </ul>
  </nav>
</header>

<section class="container">
  <h2>Generate and View Reports</h2>

  <form id="report-form">
    <div class="form-group">
      <label for="start-date">Start Date:</label>
      <input type="date" id="start-date" name="start-date" required>
    </div>
    <div class="form-group">
      <label for="end-date">End Date:</label>
      <input type="date" id="end-date" name="end-date" required>
    </div>
    <button type="submit" class="submit-btn">Generate Report</button>
  </form>

  <div id="report-results">
    <h3>Report Results</h3>
    <table>
      <thead>
      <tr>
        <th>Vehicle ID</th>
        <th>Total Fuel (L)</th>
        <th>Total Cost</th>
        <th>Date Range</th>
      </tr>
      </thead>
      <tbody id="report-table">
        <!-- Report data will be dynamically generated here -->
        <%
          // Simulate fetching reports data from the database or server
          List<Report> reports = (List<Report>) request.getAttribute("reports");
          if (reports != null) {
            for (Report report : reports) {
        %>
        <tr>
          <td><%= report.getVehicleId() %></td>
          <td><%= report.getTotalFuel() %></td>
          <td><%= report.getTotalCost() %></td>
          <td><%= report.getDateRange() %></td>
        </tr>
        <%
            }
          }
        %>
      </tbody>
    </table>
  </div>
</section>

<footer>
  <p>&copy; <%= java.time.Year.now().getValue() %> Fuel Management System | All Rights Reserved</p>
  <img src="images/fuel-pump.png" alt="Fuel Pump" class="footer-image">
</footer>

<script>
  // Handle report generation with client-side JS (optional for dynamic reports)
  document.getElementById('report-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get form values
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;

    // Example logic to fetch report data (to be replaced with actual backend code)
    const reportData = [
      { vehicleId: 'ABC123', totalFuel: 50, totalCost: 100.50, dateRange: ${startDate} - ${endDate} },
      { vehicleId: 'XYZ789', totalFuel: 75, totalCost: 150.75, dateRange: ${startDate} - ${endDate} }
    ];

    const reportTable = document.getElementById('report-table');
    reportTable.innerHTML = ''; // Clear any previous report data

    reportData.forEach(function(report) {
      const newRow = document.createElement('tr');
      newRow.innerHTML = `
        <td>${report.vehicleId}</td>
        <td>${report.totalFuel}</td>
        <td>${report.totalCost}</td>
        <td>${report.dateRange}</td>
      `;
      reportTable.appendChild(newRow);
    });
  });
</script>

</body>
</html>