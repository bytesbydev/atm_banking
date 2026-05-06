package services;

import dao.UserDAO;
import dao.AccountDAO;
import model.User;
import model.Account;

/**
 * Authentication Service for login and signup operations
 */
public class AuthService {
    private UserDAO userDAO = new UserDAO();
    private AccountDAO accountDAO = new AccountDAO();

    /**
     * Register a new user
     * @param name User name
     * @param email User email
     * @param pin User PIN
     * @return true if signup successful, false otherwise
     */
    public boolean signup(String name, String email, String pin) {
        // Validate inputs
        if (!Validator.isValidName(name)) {
            System.err.println("Invalid name! Must be at least 3 characters.");
            return false;
        }
        if (!Validator.isValidEmail(email)) {
            System.err.println("Invalid email format!");
            return false;
        }
        if (!Validator.isValidPin(pin)) {
            System.err.println("Invalid PIN! Must be 4-10 digits.");
            return false;
        }

        // Check if email already exists
        if (userDAO.emailExists(email)) {
            System.err.println("Email already registered!");
            return false;
        }

        // Create user
        User user = new User(name, email, pin);
        if (!userDAO.createUser(user)) {
            System.err.println("Error creating user!");
            return false;
        }

        // Get the created user to get the ID
        User createdUser = userDAO.getUserByEmail(email);
        if (createdUser == null) {
            System.err.println("Error retrieving created user!");
            return false;
        }

        // Create account for the user with initial balance 0
        Account account = new Account(createdUser.getId(), 0.0);
        if (!accountDAO.createAccount(account)) {
            System.err.println("Error creating account!");
            return false;
        }

        System.out.println("Signup successful!");
        return true;
    }

    /**
     * Login user
     * @param email User email
     * @param pin User PIN
     * @return User object if login successful, null otherwise
     */
    public User login(String email, String pin) {
        // Validate inputs
        if (!Validator.isValidEmail(email) || pin == null || pin.isEmpty()) {
            System.err.println("Invalid email or PIN!");
            return null;
        }

        // Get user by email
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            System.err.println("User not found!");
            return null;
        }

        // Verify PIN
        if (!user.getPin().equals(pin)) {
            System.err.println("Incorrect PIN!");
            return null;
        }

        System.out.println("Login successful!");
        return user;
    }
}