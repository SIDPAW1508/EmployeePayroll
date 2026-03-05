package com.seveneleven.employeepayroll.model;

/**
 * UserAccount class represents login credentials
 * associated with an employee.
 * 
 * Demonstrates Encapsulation by keeping fields private
 * and providing getters to access them.
 */
public class UserAccount {

    // Username used for employee login
    private String username;

    // Password for the account (ideally should be encrypted)
    private String password;

    /**
     * Constructor to initialize a UserAccount object
     * 
     * @param username the login username
     * @param password the login password
     */
    public UserAccount(String username, String password) {
        this.password = password;
        this.username = username;
    }

    /**
     * Getter method to retrieve the username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter method to retrieve the password
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Overrides the default toString() method.
     * Returns a formatted string representation of the user account.
     */
    @Override
    public String toString() {
        return "UserName: " + username +
               "\nPassword " + password;
    }
}