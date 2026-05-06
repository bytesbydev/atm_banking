package services;

import java.util.regex.Pattern;

/**
 * Validator class for input validation
 */
public class Validator {

    /**
     * Validate email format
     * @param email Email to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Validate PIN (numeric, 4-10 digits)
     * @param pin PIN to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPin(String pin) {
        if (pin == null || pin.isEmpty()) {
            return false;
        }
        return pin.matches("^[0-9]{4,10}$");
    }

    /**
     * Validate name (not empty, at least 3 characters)
     * @param name Name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 3;
    }

    /**
     * Validate amount (positive number)
     * @param amount Amount to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    /**
     * Validate amount string
     * @param amountStr Amount string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAmountString(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return false;
        }
        try {
            double amount = Double.parseDouble(amountStr);
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}