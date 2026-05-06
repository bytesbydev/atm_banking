package ui;

import model.User;
import services.TransactionService;
import services.Validator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Withdraw Frame - Modern Withdrawal Interface
 */
public class WithdrawFrame extends JFrame {
    private User user;
    private DashboardFrame parentFrame;
    private TransactionService transactionService = new TransactionService();
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton cancelButton;

    public WithdrawFrame(User user, DashboardFrame parentFrame) {
        this.user = user;
        this.parentFrame = parentFrame;

        setTitle("ATM System - Withdraw");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Color color1 = new Color(15, 32, 65);
                Color color2 = new Color(32, 58, 125);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("💸 WITHDRAW MONEY");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(80, 30, 500, 50);
        mainPanel.add(titleLabel);

        JLabel currentLabel = new JLabel("Current Balance: Rs. " + String.format("%.2f", transactionService.getBalance(user.getId())));
        currentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        currentLabel.setForeground(new Color(244, 67, 54));
        currentLabel.setBounds(80, 90, 500, 25);
        mainPanel.add(currentLabel);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        amountLabel.setForeground(new Color(220, 220, 220));
        amountLabel.setBounds(80, 160, 150, 25);
        mainPanel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(80, 190, 540, 50);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        amountField.setBackground(new Color(40, 50, 90));
        amountField.setForeground(Color.WHITE);
        amountField.setBorder(BorderFactory.createLineBorder(new Color(244, 67, 54), 2));
        amountField.setCaretColor(Color.WHITE);
        mainPanel.add(amountField);

        withdrawButton = new JButton("✓ Confirm Withdrawal");
        withdrawButton.setBounds(80, 290, 200, 50);
        withdrawButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        withdrawButton.setBackground(new Color(244, 67, 54));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBorder(BorderFactory.createEmptyBorder());
        withdrawButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdrawButton.setFocusPainted(false);
        withdrawButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                withdrawButton.setBackground(new Color(220, 50, 40));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                withdrawButton.setBackground(new Color(244, 67, 54));
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performWithdraw();
            }
        });
        mainPanel.add(withdrawButton);

        cancelButton = new JButton("✕ Cancel");
        cancelButton.setBounds(420, 290, 200, 50);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBackground(new Color(158, 158, 158));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.setFocusPainted(false);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(130, 130, 130));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(158, 158, 158));
            }
        });
        cancelButton.addActionListener(e -> closeWindow());
        mainPanel.add(cancelButton);

        add(mainPanel);
    }

    private void performWithdraw() {
        String amountStr = amountField.getText().trim();

        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter amount!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Validator.isValidAmountString(amountStr)) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount = Double.parseDouble(amountStr);

        if (transactionService.withdraw(user.getId(), amount)) {
            JOptionPane.showMessageDialog(this, "✓ Withdrawal Successful!\nAmount: Rs. " + String.format("%.2f", amount), "Success", JOptionPane.INFORMATION_MESSAGE);
            parentFrame.refreshBalance();
            closeWindow();
        } else {
            JOptionPane.showMessageDialog(this, "✗ Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeWindow() {
        parentFrame.setEnabled(true);
        this.dispose();
    }
}