-- Create the FuelManagementDB database
CREATE DATABASE IF NOT EXISTS FuelManagementDB;

-- Use the FuelManagementDB database
USE FuelManagementDB;

-- Create Fuel table
CREATE TABLE IF NOT EXISTS Fuel (
    fuel_id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    quantity DOUBLE NOT NULL
);

-- Create Transaction table
CREATE TABLE IF NOT EXISTS Transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    fuel_id INT,
    transaction_type ENUM('purchase', 'sale', 'refill') NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fuel_id) REFERENCES Fuel(fuel_id)
);
