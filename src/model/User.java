package model;

/**
 * User model representing a user in the ATM system
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String pin;

    // Constructor
    public User(int id, String name, String email, String pin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pin = pin;
    }

    public User(String name, String email, String pin) {
        this.name = name;
        this.email = email;
        this.pin = pin;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}