package ui;

import model.User;
import services.TransactionService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dashboard Frame - Modern UI Dashboard
 */
public class DashboardFrame extends JFrame {
    private User user;
    private TransactionService transactionService = new TransactionService();
    private JLabel balanceLabel;
    private JButton depositButton, withdrawButton, balanceButton, historyButton, logoutButton;

    public DashboardFrame(User user) {
        this.user = user;

        setTitle("ATM System - Dashboard");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

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

        // Top bar
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(10, 20, 50));
        topBar.setBounds(0, 0, 1100, 80);
        mainPanel.add(topBar);

        // User name in top bar
        JLabel userLabel = new JLabel("👤 " + user.getName());
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        userLabel.setForeground(new Color(26, 188, 156));
        userLabel.setBounds(30, 25, 300, 30);
        topBar.add(userLabel);

        // Welcome title
        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(50, 100, 400, 50);
        mainPanel.add(welcomeLabel);

        // Balance card
        JPanel balanceCard = new JPanel();
        balanceCard.setBackground(new Color(26, 188, 156));
        balanceCard.setBounds(50, 170, 400, 150);
        balanceCard.setLayout(null);
        balanceCard.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(balanceCard);

        JLabel balanceTitleLabel = new JLabel("💰 Available Balance");
        balanceTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        balanceTitleLabel.setForeground(new Color(200, 255, 240));
        balanceTitleLabel.setBounds(20, 20, 200, 25);
        balanceCard.add(balanceTitleLabel);

        balanceLabel = new JLabel("Rs. " + String.format("%.2f", transactionService.getBalance(user.getId())));
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(20, 50, 350, 60);
        balanceCard.add(balanceLabel);

        // Action buttons grid
        int[] xPos = {550, 750, 550, 750};
        int[] yPos = {170, 170, 340, 340};
        String[] labels = {"Deposit", "Withdraw", "Balance", "History"};
        Color[] colors = {new Color(76, 175, 80), new Color(244, 67, 54), new Color(33, 150, 243), new Color(156, 39, 176)};
        ActionListener[] actions = {
            e -> openDepositFrame(),
            e -> openWithdrawFrame(),
            e -> showBalance(),
            e -> showHistory()
        };

        JButton[] buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            buttons[i] = createActionButton(labels[i], colors[i], xPos[i], yPos[i], actions[i]);
            mainPanel.add(buttons[i]);
        }

        // Recent transactions section
        JLabel recentLabel = new JLabel("📊 Quick Stats");
        recentLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        recentLabel.setForeground(Color.WHITE);
        recentLabel.setBounds(50, 530, 300, 30);
        mainPanel.add(recentLabel);

        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(30, 50, 100));
        statsPanel.setBounds(50, 570, 950, 80);
        statsPanel.setBorder(BorderFactory.createLineBorder(new Color(26, 188, 156), 2));
        statsPanel.setLayout(new GridLayout(1, 3, 20, 0));
        mainPanel.add(statsPanel);

        JLabel stat1 = createStatLabel("Total Transactions", "View History");
        JLabel stat2 = createStatLabel("Account Status", "Active");
        JLabel stat3 = createStatLabel("Security", "🔒 Protected");
        statsPanel.add(stat1);
        statsPanel.add(stat2);
        statsPanel.add(stat3);

        // Logout Button
        logoutButton = new JButton("🚪 Logout");
        logoutButton.setBounds(900, 670, 100, 40);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutButton.setBackground(new Color(192, 57, 43));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setFocusPainted(false);
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new Color(220, 80, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(new Color(192, 57, 43));
            }
        });
        logoutButton.addActionListener(e -> logout());
        mainPanel.add(logoutButton);

        add(mainPanel);
    }

    private JButton createActionButton(String text, Color color, int x, int y, ActionListener action) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 180, 140);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(Math.min(color.getRed() + 30, 255), 
                                           Math.min(color.getGreen() + 30, 255), 
                                           Math.min(color.getBlue() + 30, 255)));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
            }
        });
        btn.addActionListener(action);
        return btn;
    }

    private JLabel createStatLabel(String title, String value) {
        JLabel label = new JLabel(title + ": " + value);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(26, 188, 156));
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private void updateBalance() {
        balanceLabel.setText("Rs. " + String.format("%.2f", transactionService.getBalance(user.getId())));
    }

    private void openDepositFrame() {
        new DepositFrame(user, this).setVisible(true);
        this.setEnabled(false);
    }

    private void openWithdrawFrame() {
        new WithdrawFrame(user, this).setVisible(true);
        this.setEnabled(false);
    }

    private void showBalance() {
        double balance = transactionService.getBalance(user.getId());
        JOptionPane.showMessageDialog(this, "💰 Your Balance: Rs. " + String.format("%.2f", balance), "Balance", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showHistory() {
        String history = transactionService.getTransactionHistory(user.getId());
        JTextArea textArea = new JTextArea(history);
        textArea.setEditable(false);
        textArea.setBackground(new Color(40, 50, 90));
        textArea.setForeground(new Color(26, 188, 156));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            this.dispose();
        }
    }

    public void refreshBalance() {
        updateBalance();
    }
}