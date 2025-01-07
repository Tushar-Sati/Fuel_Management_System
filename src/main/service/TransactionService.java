package com.fuelmanagement.service;

import com.fuelmanagement.dao.TransactionDAO;
import com.fuelmanagement.model.Transaction;

import java.util.List;

public class TransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    /**
     * Adds a new transaction.
     *
     * @param fuelId           The ID of the fuel involved in the transaction.
     * @param transactionType  The type of transaction (e.g., purchase, sale, refill).
     * @param amount           The quantity involved in the transaction.
     * @return true if the transaction is added successfully, false otherwise.
     */
    public boolean addTransaction(int fuelId, String transactionType, double amount) {
        if (fuelId <= 0 || transactionType == null || transactionType.isEmpty() || amount <= 0) {
            throw new IllegalArgumentException("Invalid transaction details");
        }

        Transaction transaction = new Transaction();
        transaction.setFuelId(fuelId);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);

        return transactionDAO.addTransaction(transaction);
    }

    /**
     * Retrieves a list of all transactions.
     *
     * @return A list of all transactions.
     */
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    /**
     * Retrieves a list of transactions for a specific fuel ID.
     *
     * @param fuelId The ID of the fuel.
     * @return A list of transactions for the specified fuel ID.
     */
    public List<Transaction> getTransactionsByFuelId(int fuelId) {
        if (fuelId <= 0) {
            throw new IllegalArgumentException("Invalid fuel ID");
        }

        return transactionDAO.getTransactionsByFuelId(fuelId);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param transactionId The ID of the transaction to delete.
     * @return true if the transaction is deleted successfully, false otherwise.
     */
    public boolean deleteTransaction(int transactionId) {
        if (transactionId <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID");
        }

        return transactionDAO.deleteTransaction(transactionId);
    }
}
