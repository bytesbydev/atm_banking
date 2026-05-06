package ui;

import services.AuthService;
import model.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * Login Frame - Modern UI with Dark Theme
 */
public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton signupButton;
    private AuthService authService = new AuthService();

    public LoginFrame() {
        setTitle("ATM System - Login");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);

        // Main panel with gradient-like background
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dark gradient background
                Color color1 = new Color(15, 32, 65);  // Dark Blue
                Color color2 = new Color(32, 58, 125); // Lighter Blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Left side decorative panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(26, 188, 156)); // Turquoise
        leftPanel.setBounds(0, 0, 400, 700);
        mainPanel.add(leftPanel);

        // Logo/Title in left panel
        JLabel logoLabel = new JLabel("💳");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 80));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBounds(0, 120, 400, 120);
        mainPanel.add(logoLabel);

        JLabel bankLabel = new JLabel("SECURE BANKING");
        bankLabel.setFont(new Font("Arial", Font.BOLD, 28));
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setHorizontalAlignment(JLabel.CENTER);
        bankLabel.setBounds(0, 240, 400, 40);
        mainPanel.add(bankLabel);

        JLabel taglineLabel = new JLabel("Fast • Secure • Reliable");
        taglineLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        taglineLabel.setForeground(new Color(220, 220, 220));
        taglineLabel.setHorizontalAlignment(JLabel.CENTER);
        taglineLabel.setBounds(0, 290, 400, 30);
        mainPanel.add(taglineLabel);

        // Right side - Login form
        JLabel titleLabel = new JLabel("Welcome Back");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(500, 80, 400, 50);
        mainPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Sign in to your account");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(180, 180, 180));
        subtitleLabel.setBounds(500, 130, 400, 25);
        mainPanel.add(subtitleLabel);

        // Email Label
        JLabel emailLabel = new JLabel("📧 Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        emailLabel.setForeground(new Color(220, 220, 220));
        emailLabel.setBounds(500, 190, 150, 20);
        mainPanel.add(emailLabel);

        // Email Field
        emailField = new JTextField();
        emailField.setBounds(500, 215, 350, 45);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBackground(new Color(40, 50, 90));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(26, 188, 156), 2));
        emailField.setCaretColor(Color.WHITE);
        mainPanel.add(emailField);

        // PIN Label
        JLabel pinLabel = new JLabel("🔐 PIN Code");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pinLabel.setForeground(new Color(220, 220, 220));
        pinLabel.setBounds(500, 280, 150, 20);
        mainPanel.add(pinLabel);

        // PIN Field
        pinField = new JPasswordField();
        pinField.setBounds(500, 305, 350, 45);
        pinField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pinField.setBackground(new Color(40, 50, 90));
        pinField.setForeground(Color.WHITE);
        pinField.setBorder(BorderFactory.createLineBorder(new Color(26, 188, 156), 2));
        pinField.setCaretColor(Color.WHITE);
        mainPanel.add(pinField);

        // Login Button
        loginButton = new JButton("Sign In");
        loginButton.setBounds(500, 390, 350, 50);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(26, 188, 156));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(22, 160, 133));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(26, 188, 156));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        mainPanel.add(loginButton);

        // Signup Button
        signupButton = new JButton("Create Account");
        signupButton.setBounds(500, 460, 350, 45);
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setBackground(new Color(52, 73, 150));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(26, 188, 156), 2));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setFocusPainted(false);
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(70, 95, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(52, 73, 150));
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignupFrame();
            }
        });
        mainPanel.add(signupButton);

        // Info label
        JLabel infoLabel = new JLabel("🔒 Your data is encrypted and secure");
        infoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        infoLabel.setForeground(new Color(150, 150, 150));
        infoLabel.setBounds(500, 530, 350, 25);
        mainPanel.add(infoLabel);

        add(mainPanel);
    }

    private void login() {
        String email = emailField.getText().trim();
        String pin = new String(pinField.getPassword());

        if (email.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email and PIN!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = authService.login(email, pin);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "✓ Welcome " + user.getName() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new DashboardFrame(user).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "✗ Invalid email or PIN!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            pinField.setText("");
        }
    }

    private void openSignupFrame() {
        new SignupFrame().setVisible(true);
        this.dispose();
    }
}