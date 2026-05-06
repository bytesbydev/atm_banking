package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for Transaction operations
 */
public class TransactionDAO {

    /**
     * Record a new transaction
     * @param userId User ID
     * @param type Transaction type (DEPOSIT, WITHDRAW)
     * @param amount Transaction amount
     * @return true if successful, false otherwise
     */
    public boolean recordTransaction(int userId, String type, double amount) {
        String sql = "INSERT INTO transactions (user_id, type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, type);
            pstmt.setDouble(3, amount);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get transaction history for a user
     * @param userId User ID
     * @return String with transaction history
     */
    public String getTransactionHistory(int userId) {
        String sql = "SELECT type, amount, date FROM transactions WHERE user_id = ? ORDER BY date DESC LIMIT 10";
        StringBuilder history = new StringBuilder();
        history.append("=== TRANSACTION HISTORY ===\n\n");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                history.append("No transactions found.\n");
            } else {
                int count = 1;
                while (rs.next()) {
                    String type = rs.getString("type");
                    double amount = rs.getDouble("amount");
                    String date = rs.getString("date");
                    history.append(count).append(". ").append(type).append(" - Rs. ")
                            .append(String.format("%.2f", amount))
                            .append(" on ").append(date).append("\n");
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            history.append("Error retrieving history.");
        }
        return history.toString();
    }
}