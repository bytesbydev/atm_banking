package ui;

import services.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Signup Frame - Modern UI with Dark Theme
 */
public class SignupFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField pinField;
    private JPasswordField confirmPinField;
    private JButton signupButton;
    private JButton backButton;
    private AuthService authService = new AuthService();

    public SignupFrame() {
        setTitle("ATM System - Create Account");
        setSize(1000, 700);
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

        // Left side decorative panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(52, 211, 153)); // Mint Green
        leftPanel.setBounds(0, 0, 400, 700);
        mainPanel.add(leftPanel);

        JLabel logoLabel = new JLabel("✨");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 80));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBounds(0, 120, 400, 120);
        mainPanel.add(logoLabel);

        JLabel createLabel = new JLabel("CREATE ACCOUNT");
        createLabel.setFont(new Font("Arial", Font.BOLD, 28));
        createLabel.setForeground(Color.WHITE);
        createLabel.setHorizontalAlignment(JLabel.CENTER);
        createLabel.setBounds(0, 240, 400, 40);
        mainPanel.add(createLabel);

        // Right side - Form
        JLabel titleLabel = new JLabel("Get Started");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(500, 50, 400, 50);
        mainPanel.add(titleLabel);

        // Full Name
        JLabel nameLabel = new JLabel("👤 Full Name");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nameLabel.setForeground(new Color(220, 220, 220));
        nameLabel.setBounds(500, 120, 150, 20);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(500, 145, 350, 40);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        nameField.setBackground(new Color(40, 50, 90));
        nameField.setForeground(Color.WHITE);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(52, 211, 153), 2));
        nameField.setCaretColor(Color.WHITE);
        mainPanel.add(nameField);

        // Email
        JLabel emailLabel = new JLabel("📧 Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        emailLabel.setForeground(new Color(220, 220, 220));
        emailLabel.setBounds(500, 200, 150, 20);
        mainPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(500, 225, 350, 40);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailField.setBackground(new Color(40, 50, 90));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(52, 211, 153), 2));
        emailField.setCaretColor(Color.WHITE);
        mainPanel.add(emailField);

        // PIN
        JLabel pinLabel = new JLabel("🔐 PIN Code (4-10 digits)");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pinLabel.setForeground(new Color(220, 220, 220));
        pinLabel.setBounds(500, 280, 200, 20);
        mainPanel.add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(500, 305, 350, 40);
        pinField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        pinField.setBackground(new Color(40, 50, 90));
        pinField.setForeground(Color.WHITE);
        pinField.setBorder(BorderFactory.createLineBorder(new Color(52, 211, 153), 2));
        pinField.setCaretColor(Color.WHITE);
        mainPanel.add(pinField);

        // Confirm PIN
        JLabel confirmLabel = new JLabel("✓ Confirm PIN");
        confirmLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        confirmLabel.setForeground(new Color(220, 220, 220));
        confirmLabel.setBounds(500, 360, 150, 20);
        mainPanel.add(confirmLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBounds(500, 385, 350, 40);
        confirmPinField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        confirmPinField.setBackground(new Color(40, 50, 90));
        confirmPinField.setForeground(Color.WHITE);
        confirmPinField.setBorder(BorderFactory.createLineBorder(new Color(52, 211, 153), 2));
        confirmPinField.setCaretColor(Color.WHITE);
        mainPanel.add(confirmPinField);

        // Signup Button
        signupButton = new JButton("Create Account");
        signupButton.setBounds(500, 460, 165, 45);
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setBackground(new Color(52, 211, 153));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setFocusPainted(false);
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(45, 180, 130));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(52, 211, 153));
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
        mainPanel.add(signupButton);

        // Back Button
        backButton = new JButton("Back to Login");
        backButton.setBounds(685, 460, 165, 45);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(100, 130, 200));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(120, 150, 220));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(100, 130, 200));
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToLogin();
            }
        });
        mainPanel.add(backButton);

        add(mainPanel);
    }

    private void signup() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String pin = new String(pinField.getPassword());
        String confirmPin = new String(confirmPinField.getPassword());

        if (name.isEmpty() || email.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!pin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(this, "PINs do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (authService.signup(name, email, pin)) {
            JOptionPane.showMessageDialog(this, "✓ Account created! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            backToLogin();
        } else {
            JOptionPane.showMessageDialog(this, "✗ Signup failed! Check your input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToLogin() {
        new LoginFrame().setVisible(true);
        this.dispose();
    }
}