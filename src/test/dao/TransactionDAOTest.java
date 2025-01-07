package dao;

import model.Transaction;
import org.junit.jupiter.api.*;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionDAOTest {

    private TransactionDAO transactionDAO;

    @BeforeAll
    public void setup() {
        transactionDAO = new TransactionDAO();
    }

    @BeforeEach
    public void clearDatabase() {
        // Clear the Transaction table before each test
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Transaction")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction();
        transaction.setFuelId(1);
        transaction.setTransactionType("purchase");
        transaction.setAmount(50.0);

        transactionDAO.addTransaction(transaction);

        List<Transaction> transactions = transactionDAO.getAllTransactions();
        assertEquals(1, transactions.size());
        assertEquals("purchase", transactions.get(0).getTransactionType());
        assertEquals(50.0, transactions.get(0).getAmount());
    }

    @Test
    public void testGetAllTransactions() {
        Transaction transaction1 = new Transaction();
        transaction1.setFuelId(1);
        transaction1.setTransactionType("sale");
        transaction1.setAmount(30.0);

        Transaction transaction2 = new Transaction();
        transaction2.setFuelId(2);
        transaction2.setTransactionType("refill");
        transaction2.setAmount(100.0);

        transactionDAO.addTransaction(transaction1);
        transactionDAO.addTransaction(transaction2);

        List<Transaction> transactions = transactionDAO.getAllTransactions();
        assertEquals(2, transactions.size());
    }

    @Test
    public void testGetTransactionsByFuelId() {
        Transaction transaction1 = new Transaction();
        transaction1.setFuelId(1);
        transaction1.setTransactionType("sale");
        transaction1.setAmount(25.0);

        Transaction transaction2 = new Transaction();
        transaction2.setFuelId(1);
        transaction2.setTransactionType("refill");
        transaction2.setAmount(50.0);

        Transaction transaction3 = new Transaction();
        transaction3.setFuelId(2);
        transaction3.setTransactionType("purchase");
        transaction3.setAmount(75.0);

        transactionDAO.addTransaction(transaction1);
        transactionDAO.addTransaction(transaction2);
        transactionDAO.addTransaction(transaction3);

        List<Transaction> transactions = transactionDAO.getTransactionsByFuelId(1);
        assertEquals(2, transactions.size());
   
