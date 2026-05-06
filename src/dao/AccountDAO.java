package dao;

import model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for Account operations
 */
public class AccountDAO {

    /**
     * Create a new account
     * @param account Account object to create
     * @return true if successful, false otherwise
     */
    public boolean createAccount(Account account) {
        String sql = "INSERT INTO accounts (user_id, balance) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, account.getUserId());
            pstmt.setDouble(2, account.getBalance());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get account by user ID
     * @param userId User ID
     * @return Account object or null if not found
     */
    public Account getAccountByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Update account balance
     * @param accountId Account ID
     * @param newBalance New balance
     * @return true if successful, false otherwise
     */
    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get account by account ID
     * @param accountId Account ID
     * @return Account object or null if not found
     */
    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}