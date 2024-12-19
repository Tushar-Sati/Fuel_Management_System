# Real Estate Management System

A comprehensive Real Estate Management System built with Java and MySQL for managing properties, clients, and transactions. This project demonstrates essential CRUD operations with database connectivity using JDBC.

## Table of Contents
- [Application Features](#application-features)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Project](#running-the-project)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)

## Application Features

This application includes the following features:

1. **Property Management**:
   - Add, update, and delete property records.
   - Categorize properties by type, location, and status (available, sold, rented).

2. **Client Management**:
   - Maintain a directory of clients and their contact details.
   - Track client interactions and property interests.

3. **Transaction Management**:
   - Record transactions (sale or lease agreements).
   - Generate transaction summaries and detailed reports.

4. **Dashboard**:
   - View key metrics, including total properties managed, active clients, and recent transactions.

5. **Responsive Design** (Optional Web Interface):
   - User-friendly interface accessible across devices (desktop, tablet, mobile).

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 8 or higher (This project uses JDK 23).
2. **IntelliJ IDEA** (or any Java-compatible IDE).
3. **MySQL Database**: Ensure MySQL is installed and running.
4. **MySQL Connector/J**: The JDBC driver for MySQL.

## Project Structure

The project is organized as follows:

```
RealEstateManagementSystem/
├── src/
│   ├── main/
│   │   ├── dao/              # Data Access Objects (for database operations)
│   │   ├── model/            # Database models (e.g., Property, Client, Transaction)
│   │   ├── util/             # Utility classes (e.g., DBConnection)
│   │   └── Main.java         # Main class for running the project
├── lib/                      # MySQL JDBC driver (Connector/J .jar file)
├── db/                       # Database setup script
│   └── real_estate_db.sql
├── web_app/                  # Frontend web resources
    ├── css/                  # Stylesheets for the project
    │   ├── styles.css        # Main stylesheet
    ├── images/               # Images used in the web application
    ├── js/                   # JavaScript files
    │   ├── script.js         # Main JavaScript file
    ├── properties.html       # Page to view and manage properties
    ├── clients.html          # Page to view and manage clients
    ├── transactions.html     # Page to view and manage transactions
    ├── index.html            # Home page (main entry point for the web app)
```

- **`dao/`**: Contains classes for database operations.
- **`model/`**: Contains Java classes representing `Property`, `Client`, and `Transaction`.
- **`util/`**: Contains utility classes such as `DBConnection` for managing database connections.
- **`db/`**: Contains SQL script to set up the MySQL database and tables.
- **`web_app/`**: Contains the resources for the optional web application interface.

## Setup Instructions

Follow these steps to set up and run the project:

### 1. Clone or Download the Project
- Clone the repository or download the project files.

### 2. Set Up the Database

1. Open MySQL Workbench or MySQL Command Line.
2. Run the SQL script located at `db/real_estate_db.sql` to create the necessary database and tables.

    ```sql
    CREATE DATABASE IF NOT EXISTS RealEstateDB;

    USE RealEstateDB;

    CREATE TABLE IF NOT EXISTS Property (
        property_id INT AUTO_INCREMENT PRIMARY KEY,
        address VARCHAR(255) NOT NULL,
        type VARCHAR(50) NOT NULL,
        status ENUM('available', 'sold', 'rented') NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Client (
        client_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        contact_info VARCHAR(255) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Transaction (
        transaction_id INT AUTO_INCREMENT PRIMARY KEY,
        property_id INT,
        client_id INT,
        transaction_type ENUM('sale', 'lease') NOT NULL,
        amount DECIMAL(15, 2) NOT NULL,
        transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (property_id) REFERENCES Property(property_id),
        FOREIGN KEY (client_id) REFERENCES Client(client_id)
    );
    ```

3. **Note**: Update the MySQL username and password in the `DBConnection.java` file to match your MySQL setup.

### 3. Configure Database Connection

- Open `src/util/DBConnection.java` and update the following lines with your MySQL credentials:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/RealEstateDB";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    ```

### 4. Add MySQL Connector/J to the Project

- Download the MySQL Connector/J `.jar` file from [MySQL's official site](https://dev.mysql.com/downloads/connector/j/).
- Place the `.jar` file in the `lib/` folder.
- In IntelliJ, right-click the `.jar` file and select `Add as Library` to include it in your project dependencies.

## Running the Project

1. **Open IntelliJ IDEA**.
2. **Open the project folder** in IntelliJ.
3. **Set the JDK version**:
    - Go to `File > Project Structure > Project`, and set the SDK to the version of Java you have installed (JDK 23 is recommended).
4. **Compile and Run**:
    - Right-click on `Main.java` (located in `src/main/`) and select `Run 'Main'`.

## Usage

Once the project is running, you will see a console-based menu for the Real Estate Management System. Options include:

1. **Add Property**: Add a new property to the database.
2. **View Properties**: View all properties, filtered by type or status.
3. **Add Client**: Register a new client.
4. **Record Transaction**: Log a sale or lease transaction.
5. **View Transactions**: Display transaction history.
6. **Exit**: Exit the application.

### Example Usage

- **Add Property**: Enter details such as address, type, and status.
- **Add Client**: Provide the client’s name and contact details.
- **Record Transaction**: Select a property and client, then specify transaction type and amount.
- **View Transactions**: Displays all transactions with filters for type or date.

## Troubleshooting

- **Database Connection Error**:
    - Ensure MySQL is running, and the `DBConnection` class has the correct username, password, and database URL.

- **MySQL Connector Error**:
    - If IntelliJ cannot find the MySQL Connector/J, ensure that it is added as a library in your project.

- **Table Not Found Error**:
    - Make sure you have run the SQL script `real_estate_db.sql` to create the tables in MySQL.

---

This guide should help you get up and running with the Real Estate Management System project. For any further issues, consult IntelliJ or MySQL documentation.
