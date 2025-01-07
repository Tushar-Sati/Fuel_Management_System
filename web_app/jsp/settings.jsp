<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Settings</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
  <img src="images/fuel-logo.png" alt="Fuel Management Logo" class="logo">
  <h1>Settings</h1>
  <nav>
    <ul>
      <li><a href="index.jsp">Home</a></li>
      <li><a href="fuel-records.jsp">Fuel Records</a></li>
      <li><a href="reports.jsp">Reports</a></li>
    </ul>
  </nav>
</header>

<section class="container">
  <h2>System Settings</h2>
  <form id="settings-form" method="post" action="saveSettings.jsp">
    <div class="form-group">
      <label for="currency">Preferred Currency:</label>
      <select id="currency" name="currency">
        <option value="USD">USD - US Dollar</option>
        <option value="EUR">EUR - Euro</option>
        <option value="GBP">GBP - British Pound</option>
        <option value="INR">INR - Indian Rupee</option>
        <option value="JPY">JPY - Japanese Yen</option>
      </select>
    </div>
    <div class="form-group">
      <label for="language">Preferred Language:</label>
      <select id="language" name="language">
        <option value="en">English</option>
        <option value="es">Spanish</option>
        <option value="fr">French</option>
        <option value="de">German</option>
        <option value="zh">Chinese</option>
      </select>
    </div>
    <div class="form-group">
      <label for="records-per-page">Records Per Page:</label>
      <input type="number" id="records-per-page" name="records-per-page" min="5" max="50" value="10">
    </div>
    <button type="submit" class="submit-btn">Save Settings</button>
  </form>
</section>

<footer>
  <p>&copy; <%= java.time.Year.now().getValue() %> Fuel Management System | All Rights Reserved</p>
  <img src="images/fuel-pump.png" alt="Fuel Pump" class="footer-image">
</footer>

<script>
  // Load and save settings using localStorage
  const settingsForm = document.getElementById('settings-form');

  // Load settings from localStorage on page load
  window.addEventListener('load', () => {
    const currency = localStorage.getItem('currency');
    const language = localStorage.getItem('language');
    const recordsPerPage = localStorage.getItem('recordsPerPage');

    if (currency) document.getElementById('currency').value = currency;
    if (language) document.getElementById('language').value = language;
    if (recordsPerPage) document.getElementById('records-per-page').value = recordsPerPage;
  });

  // Save settings to localStorage on form submit
  settingsForm?.addEventListener('submit', (e) => {
    e.preventDefault();
    localStorage.setItem('currency', document.getElementById('currency').value);
    localStorage.setItem('language', document.getElementById('language').value);
    localStorage.setItem('recordsPerPage', document.getElementById('records-per-page').value);
    alert('Settings saved successfully!');
  });
</script>

</body>
</html>