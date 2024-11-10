
# Fuel Management System

A simple Fuel Management System built with Java and MySQL for managing fuel types, quantities, and transactions. This project demonstrates basic CRUD operations with database connectivity using JDBC.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Project](#running-the-project)
- [Usage](#usage)
- [Troubleshooting](#troubleshooting)

## Prerequisites

Before you can run the project, make sure you have the following installed:

1. **Java Development Kit (JDK)**: Version 8 or higher (This project uses JDK 23).
2. **IntelliJ IDEA**: (or any Java-compatible IDE).
3. **MySQL Database**: Make sure MySQL is installed and running on your system.
4. **MySQL Connector/J**: The JDBC driver for MySQL.

## Project Structure

The project is organized as follows:

```
FuelManagementSystem/
├── src/
│   ├── main/
│   │   ├── dao/              # Data Access Objects (for database operations)
│   │   ├── model/            # Database models (e.g., Fuel, Transaction)
│   │   ├── util/             # Utility classes (e.g., DBConnection)
│   │   └── Main.java         # Main class for running the project
├── lib/                      # MySQL JDBC driver (Connector/J .jar file)
└── db/                       # Database setup script
    └── fuel_management_db.sql
```

- **`dao/`**: Contains classes for database operations.
- **`model/`**: Contains Java classes representing the `Fuel` and `Transaction` tables.
- **`util/`**: Contains utility classes such as `DBConnection` for managing database connections.
- **`db/`**: Contains SQL script to set up the MySQL database and tables.

## Setup Instructions

Follow these steps to set up and run the project:

### 1. Clone or Download the Project
- Clone the repository or download the project files.

### 2. Set Up the Database

1. Open MySQL Workbench or MySQL Command Line.
2. Run the SQL script located at `db/fuel_management_db.sql` to create the necessary database and tables.

    ```sql
    -- Use this SQL script to set up the database
    
    CREATE DATABASE IF NOT EXISTS FuelManagementDB;

    USE FuelManagementDB;

    CREATE TABLE IF NOT EXISTS Fuel (
        fuel_id INT AUTO_INCREMENT PRIMARY KEY,
        type VARCHAR(50) NOT NULL,
        quantity DOUBLE NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Transaction (
        transaction_id INT AUTO_INCREMENT PRIMARY KEY,
        fuel_id INT,
        transaction_type ENUM('purchase', 'sale', 'refill') NOT NULL,
        amount DOUBLE NOT NULL,
        transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (fuel_id) REFERENCES Fuel(fuel_id)
    );
    ```

3. **Note**: Make sure to update the MySQL username and password in the `DBConnection.java` file to match your MySQL setup.

### 3. Configure Database Connection

- Open `src/util/DBConnection.java` and update the following lines with your MySQL credentials:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/FuelManagementDB";
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

Once the project is running, you will see a console-based menu for the Fuel Management System. Here are the options available:

1. **Add Fuel**: Allows you to add a new fuel type and quantity.
2. **View All Fuels**: Displays all fuel types and their quantities.
3. **Add Transaction**: Records a transaction for a fuel type (purchase, sale, or refill).
4. **View All Transactions**: Displays all transaction history.
5. **Exit**: Exits the application.

### Example Usage

- **Add Fuel**: When prompted, enter the type of fuel (e.g., Diesel) and the quantity.
- **Add Transaction**: Enter the fuel ID, transaction type (purchase, sale, or refill), and the amount.
- **View Transactions**: Shows a list of all transactions.

## Troubleshooting

- **Database Connection Error**:
    - Ensure MySQL is running, and the `DBConnection` class has the correct username, password, and database URL.

- **MySQL Connector Error**:
    - If IntelliJ cannot find the MySQL Connector/J, ensure that it is added as a library in your project.

- **Table Not Found Error**:
    - Make sure you have run the SQL script `fuel_management_db.sql` to create the tables in MySQL.

---

This guide should help you get up and running with the Fuel Management System project. For any further issues, please consult the error messages or documentation for IntelliJ and MySQL.