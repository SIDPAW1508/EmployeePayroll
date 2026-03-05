package com.seveneleven.employeepayroll.model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Employee class represents an employee in the payroll system.
 * Demonstrates encapsulation and composition (Employee has a UserAccount).
 */
public class Employee {

    // Unique employee identifier
    private String empId;

    // Employee full name
    private String name;

    // Employee email address
    private String email;

    // Employee phone number
    private String phone;

    // Composition: Employee contains a UserAccount object
    private UserAccount account;

    /**
     * Constructor to initialize Employee object
     * 
     * @param empId   Unique employee ID
     * @param name    Employee name
     * @param email   Employee email
     * @param phone   Employee phone number
     * @param account UserAccount associated with employee
     */
    public Employee(String empId, String name, String email, String phone, UserAccount account) {
        this.email = email;
        this.empId = empId;
        this.name = name;
        this.phone = phone;
        this.account = account;
    }

    /**
     * Overrides the default toString() method.
     * Returns a formatted string representation of the employee details.
     */
    @Override
    public String toString() {
        return "Employee ID: " + empId +
               "\nName: " + name +
               "\nEmail: " + email +
               "\nPhone: " + phone +
               "\nUsername: " + account.getUsername();
    }

    /**
     * Saves employee data to a file.
     * Data is appended to "employees.txt".
     * 
     * Format:
     * empId,name,email,phone,username
     * 
     * @throws IOException if file writing fails
     */
    public void persist() throws IOException {

        // FileWriter in append mode (true) so existing data is not overwritten
        FileWriter writer = new FileWriter("employees.txt", true);

        // Write employee data in CSV format
        writer.write(empId + "," + name + "," + email + "," + phone + "," + account.getUsername() + "\n");

        // Close the file to release system resources
        writer.close();
    }
}