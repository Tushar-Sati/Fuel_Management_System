# User Manual for Fuel Management System

## Overview
The Fuel Management System is a Java-based application designed to manage fuel inventory, transactions, and user registrations. This document provides step-by-step instructions for using the system's features.

---

## System Requirements
- **Java Development Kit (JDK)**: Version 8 or higher.
- **MySQL Database**: Ensure MySQL is installed and running.
- **Apache Tomcat**: For deploying the web application.
- **Web Browser**: Any modern browser for accessing the web interface.

---

## Application Features

### 1. User Management
- **Registration**:
  - Navigate to the `registration.jsp` page.
  - Enter your username, email, and password.
  - Click the "Register" button to create an account.
- **Profile Viewing**:
  - After registration, you will be redirected to your profile page.
  - View your username and email information.

### 2. Fuel Management
- **Add Fuel**:
  - Use the `Add Fuel` feature to insert new fuel types and their quantities.
  - Enter the fuel type (e.g., Diesel, Petrol) and quantity.
- **View All Fuels**:
  - Navigate to the `list.jsp` page to see a list of all available fuels.
- **Update Fuel Quantity**:
  - Select a fuel from the list and update its quantity.
- **Delete Fuel**:
  - Remove unwanted fuel entries by selecting and deleting them.

### 3. Transaction Management
- **Add Transaction**:
  - Record a transaction by specifying the fuel ID, transaction type (purchase, sale, or refill), and amount.
- **View Transactions**:
  - Navigate to the `transactions.jsp` page to view all recorded transactions.
- **Filter by Fuel ID**:
  - Retrieve specific transactions by providing the fuel ID.

---

## How to Run the Application

### 1. Setup the Database
1. Open MySQL Workbench or Command Line.
2. Execute the `fuel_management_db.sql` and `user_management_db.sql` scripts to set up the database and tables.

### 2. Configure Database Connection
1. Open the `DBConnection.java` file in the `util` package.
2. Update the following lines with your MySQL credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/FuelManagementDB";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";
   ```

### 3. Deploy the Application
1. Package the application as a WAR file.
2. Deploy the WAR file to Apache Tomcat.
3. Access the application at: `http://localhost:8080/FuelManagementSystem/`

---

## Troubleshooting

### 1. Database Connection Errors
- **Ensure MySQL is running**.
- **Verify credentials in `DBConnection.java`**.

### 2. Missing Tables or Data
- Ensure the SQL scripts (`fuel_management_db.sql` and `user_management_db.sql`) are executed correctly.

### 3. Application Not Starting
- Verify that Apache Tomcat is running.
- Check the logs for deployment errors.

---

## Contact
For support or issues, contact the development team at `support@fuelmanagementsystem.com`.

