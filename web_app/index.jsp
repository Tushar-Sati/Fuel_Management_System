<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Fuel Management System - Home</title>
  <link rel="stylesheet" href="<c:url value='/css/styles.css' />">
</head>
<body>
<header>
  <img src="<c:url value='/images/fuel-logo.png' />" alt="Fuel Management Logo" class="logo">
  <h1>Fuel Management System</h1>
  <nav>
    <ul>
      <li><a href="<c:url value='/index.jsp' />">Home</a></li>
      <li><a href="<c:url value='/fuel-records.jsp' />">Fuel Records</a></li>
      <li><a href="<c:url value='/reports.jsp' />">Reports</a></li>
      <li><a href="<c:url value='/settings.jsp' />">Settings</a></li>
    </ul>
  </nav>
</header>

<section class="hero">
  <h2>Welcome to the Fuel Management System</h2>
  <p>Efficiently manage and track your fuel usage and transactions.</p>
  <a href="<c:url value='/fuel-records.jsp' />" class="cta-btn">Start Managing Fuel Records</a>
</section>

<section class="features">
  <div class="feature">
    <img src="<c:url value='/images/fuel-icon.png' />" alt="Fuel Records Icon">
    <h3>Fuel Records</h3>
    <p>Record and track every fuel transaction in real-time.</p>
  </div>
  <div class="feature">
    <img src="<c:url value='/images/reports-icon.png' />" alt="Reports Icon">
    <h3>Reports</h3>
    <p>Generate detailed reports for better insights and decision making.</p>
  </div>
  <div class="feature">
    <img src="<c:url value='/images/settings-icon.png' />" alt="Settings Icon">
    <h3>Settings</h3>
    <p>Customize the system according to your preferences.</p>
  </div>
</section>

<footer>
  <p>&copy; 2024 Fuel Management System | All Rights Reserved</p>
  <img src="<c:url value='/images/fuel-pump.png' />" alt="Fuel Pump" class="footer-image">
</footer>
<script src="<c:url value='/js/script.js' />"></script>
</body>
</html>
