<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Fuel Records</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
  <img src="images/fuel-logo.png" alt="Fuel Management Logo" class="logo">
  <h1>Fuel Records</h1>
  <nav>
    <ul>
      <li><a href="index.jsp">Home</a></li>
      <li><a href="reports.jsp">Reports</a></li>
      <li><a href="settings.jsp">Settings</a></li>
    </ul>
  </nav>
</header>

<section class="container">
  <h2>Manage Fuel Transactions</h2>
  <form id="fuel-form">
    <div class="form-group">
      <label for="vehicle-id">Vehicle ID:</label>
      <input type="text" id="vehicle-id" name="vehicle-id" required>
    </div>
    <div class="form-group">
      <label for="fuel-amount">Fuel Amount (liters):</label>
      <input type="number" id="fuel-amount" name="fuel-amount" required>
    </div>
    <div class="form-group">
      <label for="fuel-price">Fuel Price (per liter):</label>
      <input type="number" id="fuel-price" name="fuel-price" required>
    </div>
    <div class="form-group">
      <label for="transaction-date">Date:</label>
      <input type="date" id="transaction-date" name="transaction-date" required>
    </div>
    <button type="submit" class="submit-btn">Submit Transaction</button>
  </form>

  <div id="fuel-records">
    <h3>Fuel Records</h3>
    <table>
      <thead>
      <tr>
        <th>Vehicle ID</th>
        <th>Fuel Amount (L)</th>
        <th>Fuel Price</th>
        <th>Total Cost</th>
        <th>Date</th>
      </tr>
      </thead>
      <tbody id="records-table">
        <!-- Existing Fuel Records Loaded from the Database -->
        <%
          // Simulate fetching records from the database
          List<FuelRecord> fuelRecords = (List<FuelRecord>) request.getAttribute("fuelRecords");
          if (fuelRecords != null) {
            for (FuelRecord record : fuelRecords) {
        %>
        <tr>
          <td><%= record.getVehicleId() %></td>
          <td><%= record.getFuelAmount() %></td>
          <td><%= record.getFuelPrice() %></td>
          <td><%= record.getTotalCost() %></td>
          <td><%= record.getTransactionDate() %></td>
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
  // Client-side JavaScript to dynamically add fuel records
  document.getElementById('fuel-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get form values
    const vehicleId = document.getElementById('vehicle-id').value;
    const fuelAmount = parseFloat(document.getElementById('fuel-amount').value);
    const fuelPrice = parseFloat(document.getElementById('fuel-price').value);
    const transactionDate = document.getElementById('transaction-date').value;

    // Calculate total cost
    const totalCost = (fuelAmount * fuelPrice).toFixed(2);

    // Add a new row to the table
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
      <td>${vehicleId}</td>
      <td>${fuelAmount}</td>
      <td>${fuelPrice}</td>
      <td>${totalCost}</td>
      <td>${transactionDate}</td>
    `;
    document.getElementById('records-table').appendChild(newRow);

    // Clear form
    document.getElementById('fuel-form').reset();
  });
</script>
</body>
</html>