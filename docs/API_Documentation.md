# API Documentation for Fuel Management System

## Overview
This document provides the API details for the Fuel Management System, including endpoints for managing fuels, transactions, and user registrations.

## Base URL
All API endpoints are prefixed with:
```
http://localhost:8080/FuelManagementSystem/api
```

## Endpoints

### 1. Fuel Management
- **GET /fuels**
  - Description: Retrieve all fuels.
  - Response: JSON array of all fuels.
  - Example Response:
    ```json
    [
      { "fuelId": 1, "type": "Petrol", "quantity": 500.0 },
      { "fuelId": 2, "type": "Diesel", "quantity": 300.0 }
    ]
    ```
- **POST /fuels**
  - Description: Add a new fuel.
  - Request Body:
    ```json
    { "type": "CNG", "quantity": 200.0 }
    ```
  - Response: Status 201 Created.

- **PUT /fuels/{fuelId}**
  - Description: Update the quantity of a specific fuel.
  - Request Body:
    ```json
    { "quantity": 400.0 }
    ```
  - Response: Status 200 OK.

- **DELETE /fuels/{fuelId}**
  - Description: Delete a specific fuel.
  - Response: Status 204 No Content.

### 2. Transaction Management
- **GET /transactions**
  - Description: Retrieve all transactions.
  - Response: JSON array of all transactions.

- **GET /transactions/{fuelId}**
  - Description: Retrieve transactions for a specific fuel.
  - Response: JSON array of transactions.

- **POST /transactions**
  - Description: Add a new transaction.
  - Request Body:
    ```json
    { "fuelId": 1, "transactionType": "purchase", "amount": 100.0 }
    ```
  - Response: Status 201 Created.

### 3. User Management
- **POST /users**
  - Description: Register a new user.
  - Request Body:
    ```json
    { "username": "john_doe", "email": "john@example.com", "password": "securepassword" }
    ```
  - Response: Status 201 Created.

- **GET /users/{username}**
  - Description: Retrieve details of a specific user by username.
  - Response:
    ```json
    { "userId": 1, "username": "john_doe", "email": "john@example.com" }
    ```

