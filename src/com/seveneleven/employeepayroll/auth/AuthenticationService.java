package com.seveneleven.employeepayroll.auth;

import java.util.*;

import com.seveneleven.employeepayroll.session.Session;

/**
 * AuthenticationService
 * 
 * This class handles user authentication and authorization.
 * Responsibilities:
 * - Register new users
 * - Authenticate users during login
 * - Limit login attempts
 * - Create a session for successfully logged-in users
 * - Display dashboards based on user roles
 */
public class AuthenticationService {

    // Stores registered users in memory (username -> User object)
    private Map<String, User> users = new HashMap<>();

    // Maximum number of login attempts allowed
    private int maxAttempts = 3;

    // Scanner object to take user input from console
    Scanner sc = new Scanner(System.in);

    /**
     * registerUser()
     * 
     * Registers a new user by collecting username, password,
     * and role (EMPLOYEE or MANAGER).
     */
    public void registerUser() {

        // Ask user for username
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        // Ask user for password
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Ask user for role
        System.out.print("Enter role (EMPLOYEE/MANAGER): ");
        String role = sc.nextLine();

        // Create a RegularEmployee user if role is EMPLOYEE
        if(role.equalsIgnoreCase("EMPLOYEE")) {
            users.put(username, new RegularEmployee(username, password));
        }

        // Create a Manager user if role is MANAGER
        else if(role.equalsIgnoreCase("MANAGER")) {
            users.put(username, new Manager(username, password));
        }

        // Handle invalid role input
        else {
            System.out.println("Invalid role.");
            return;
        }

        // Confirmation message
        System.out.println("User registered successfully!");
    }

    /**
     * login()
     * 
     * Authenticates the user by verifying username and password.
     * Allows a maximum of 3 login attempts.
     * If login is successful, a new Session is created.
     * 
     * @return Session object if login successful, otherwise null
     */
    public Session login() {

        int attempts = 0;

        // Loop until max attempts reached
        while(attempts < maxAttempts) {

            // Get username input
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            // Get password input
            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            // Retrieve user from map
            User user = users.get(username);

            // Validate credentials
            if(user != null && user.authenticate(username, password)) {

                System.out.println("\nLogin Successful!");
                System.out.println("Role: " + user.getRole());

                // Create a session for logged-in user
                Session session = new Session(username);

                // Show dashboard according to role
                showDashboard(user.getRole());

                return session;
            }

            // Increment failed attempts
            attempts++;

            System.out.println("Invalid credentials. Attempts left: " + (maxAttempts - attempts));
        }

        // Lock account after exceeding max attempts
        System.out.println("Account locked due to multiple failed attempts.");

        return null;
    }

    /**
     * showDashboard()
     * 
     * Displays different dashboards depending on user role.
     * 
     * @param role User role (EMPLOYEE or MANAGER)
     */
    private void showDashboard(String role) {

        System.out.println("\n======= DASHBOARD =======");

        // Employee dashboard options
        if(role.equalsIgnoreCase("EMPLOYEE")) {
            System.out.println("Employee Dashboard");
            System.out.println("View Payslip | Update Profile");
        }

        // Manager dashboard options
        else if(role.equalsIgnoreCase("MANAGER")) {
            System.out.println("Manager Dashboard");
            System.out.println("Approve Leaves | View Reports");
        }
    }
}