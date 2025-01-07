import dao.FuelDAO;
import dao.TransactionDAO;
import model.Fuel;
import model.Transaction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuelDAO fuelDAO = new FuelDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Fuel Management System");
            System.out.println("1. Add Fuel");
            System.out.println("2. View All Fuels");
            System.out.println("3. Add Transaction");
            System.out.println("4. View All Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter fuel type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter fuel quantity: ");
                    double quantity = scanner.nextDouble();
                    Fuel fuel = new Fuel(type, quantity);
                    fuelDAO.addFuel(fuel);
                    System.out.println("Fuel added successfully.");
                    break;

                case 2:
                    System.out.println("Fuels Available:");
                    fuelDAO.getAllFuels().forEach(f ->
                            System.out.println("Fuel ID: " + f.getFuelId() + ", Type: " + f.getType() + ", Quantity: " + f.getQuantity()));
                    break;

                case 3:
                    System.out.print("Enter fuel ID for transaction: ");
                    int fuelId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter transaction type (purchase, sale, refill): ");
                    String transactionType = scanner.nextLine();
                    System.out.print("Enter transaction amount: ");
                    double amount = scanner.nextDouble();
                    Transaction transaction = new Transaction(fuelId, transactionType, amount);
                    transactionDAO.addTransaction(transaction);
                    System.out.println("Transaction added successfully.");
                    break;

                case 4:
                    System.out.println("Transaction History:");
                    transactionDAO.getAllTransactions().forEach(t ->
                            System.out.println("Transaction ID: " + t.getTransactionId() +
                                    ", Fuel ID: " + t.getFuelId() +
                                    ", Type: " + t.getTransactionType() +
                                    ", Amount: " + t.getAmount() +
                                    ", Date: " + t.getTransactionDate()));
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
