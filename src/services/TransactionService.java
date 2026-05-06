package services;

import dao.AccountDAO;
import dao.TransactionDAO;
import model.Account;

/**
 * Transaction Service for deposit, withdraw, and balance operations
 */
public class TransactionService {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    /**
     * Deposit money to account
     * @param userId User ID
     * @param amount Amount to deposit
     * @return true if successful, false otherwise
     */
    public boolean deposit(int userId, double amount) {
        // Validate amount
        if (!Validator.isValidAmount(amount)) {
            System.err.println("Invalid amount! Amount must be positive.");
            return false;
        }

        // Get account
        Account account = accountDAO.getAccountByUserId(userId);
        if (account == null) {
            System.err.println("Account not found!");
            return false;
        }

        // Update balance
        double newBalance = account.getBalance() + amount;
        if (!accountDAO.updateBalance(account.getId(), newBalance)) {
            System.err.println("Error updating balance!");
            return false;
        }

        // Record transaction
        if (!transactionDAO.recordTransaction(userId, "DEPOSIT", amount)) {
            System.err.println("Error recording transaction!");
            return false;
        }

        System.out.println("Deposit successful! New balance: Rs. " + String.format("%.2f", newBalance));
        return true;
    }

    /**
     * Withdraw money from account
     * @param userId User ID
     * @param amount Amount to withdraw
     * @return true if successful, false otherwise
     */
    public boolean withdraw(int userId, double amount) {
        // Validate amount
        if (!Validator.isValidAmount(amount)) {
            System.err.println("Invalid amount! Amount must be positive.");
            return false;
        }

        // Get account
        Account account = accountDAO.getAccountByUserId(userId);
        if (account == null) {
            System.err.println("Account not found!");
            return false;
        }

        // Check sufficient balance
        if (account.getBalance() < amount) {
            System.err.println("Insufficient balance! Current balance: Rs. " +
                    String.format("%.2f", account.getBalance()));
            return false;
        }

        // Update balance
        double newBalance = account.getBalance() - amount;
        if (!accountDAO.updateBalance(account.getId(), newBalance)) {
            System.err.println("Error updating balance!");
            return false;
        }

        // Record transaction
        if (!transactionDAO.recordTransaction(userId, "WITHDRAW", amount)) {
            System.err.println("Error recording transaction!");
            return false;
        }

        System.out.println("Withdrawal successful! New balance: Rs. " + String.format("%.2f", newBalance));
        return true;
    }

    /**
     * Get account balance
     * @param userId User ID
     * @return balance if found, -1 if error
     */
    public double getBalance(int userId) {
        Account account = accountDAO.getAccountByUserId(userId);
        if (account == null) {
            System.err.println("Account not found!");
            return -1;
        }
        return account.getBalance();
    }

    /**
     * Get transaction history
     * @param userId User ID
     * @return Transaction history string
     */
    public String getTransactionHistory(int userId) {
        return transactionDAO.getTransactionHistory(userId);
    }
}