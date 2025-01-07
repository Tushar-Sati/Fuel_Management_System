package service;

import dao.TransactionDAO;
import model.Transaction;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTransaction_Success() {
        Transaction transaction = new Transaction();
        transaction.setFuelId(1);
        transaction.setTransactionType("purchase");
        transaction.setAmount(100.0);

        when(transactionDAO.addTransaction(transaction)).thenReturn(true);

        boolean result = transactionService.addTransaction(1, "purchase", 100.0);
        assertTrue(result);

        verify(transactionDAO, times(1)).addTransaction(any(Transaction.class));
    }

    @Test
    public void testAddTransaction_InvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.addTransaction(-1, null, -50.0);
        });

        assertEquals("Invalid transaction details", exception.getMessage());
        verify(transactionDAO, never()).addTransaction(any(Transaction.class));
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(1, 1, "purchase", 50.0));
        mockTransactions.add(new Transaction(2, 2, "sale", 30.0));

        when(transactionDAO.getAllTransactions()).thenReturn(mockTransactions);

        List<Transaction> transactions = transactionService.getAllTransactions();
        assertNotNull(transactions);
        assertEquals(2, transactions.size());

        verify(transactionDAO, times(1)).getAllTransactions();
    }

    @Test
    public void testGetTransactionsByFuelId_Success() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(1, 1, "purchase", 50.0));
        mockTransactions.add(new Transaction(2, 1, "sale", 30.0));

        when(transactionDAO.getTransactionsByFuelId(1)).thenReturn(mockTransactions);

        List<Transaction> transactions = transactionService.getTransactionsByFuelId(1);
        assertNotNull(transactions);
        assertEquals(2, transactions.size());

        verify(transactionDAO, times(1)).getTransactionsByFuelId(1);
    }

    @Test
    public void testGetTransactionsByFuelId_InvalidFuelId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.getTransactionsByFuelId(-1);
        });

        assertEquals("Invalid fuel ID", exception.getMessage());
        verify(transactionDAO, never()).getTransactionsByFuelId(anyInt());
    }

    @Test
    public void testDeleteTransaction_Success() {
        when(transactionDAO.deleteTransaction(1)).thenReturn(true);

        boolean result = transactionService.deleteTransaction(1);
        assertTrue(result);

        verify(transactionDAO, times(1)).deleteTransaction(1);
    }

    @Test
    public void testDeleteTransaction_InvalidTransactionId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deleteTransaction(-1);
        });

        assertEquals("Invalid transaction ID", exception.getMessage());
        verify(transactionDAO, never()).deleteTransaction(anyInt());
    }
}
