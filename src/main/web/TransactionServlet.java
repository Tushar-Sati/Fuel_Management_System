package web;

import dao.TransactionDAO;
import model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTransactions(request, response);
                break;
            case "details":
                showTransactionDetails(request, response);
                break;
            default:
                listTransactions(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addTransaction(request, response);
        }
    }

    private void listTransactions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/jsp/transaction/list.jsp").forward(request, response);
    }

    private void showTransactionDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = transactionDAO.getTransactionById(id);
        if (transaction != null) {
            request.setAttribute("transaction", transaction);
            request.getRequestDispatcher("/jsp/transaction/details.jsp").forward(request, response);
        } else {
            response.getWriter().write("Transaction not found.");
        }
    }

    private void addTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fuelType = request.getParameter("fuelType");
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String customerName = request.getParameter("customerName");

        Transaction transaction = new Transaction(0, fuelType, quantity, price, customerName);
        boolean success = transactionDAO.addTransaction(transaction);

        if (success) {
            response.sendRedirect("transaction?action=list");
        } else {
            response.getWriter().write("Error: Could not add transaction record.");
        }
    }
}
