package com.seveneleven.employeepayroll;

import com.seveneleven.employeepayroll.exception.Validator;
import com.seveneleven.employeepayroll.exception.ValidationException;
import java.util.Scanner;

public class EmployeeRegistrationApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== USE CASE 6: INPUT VALIDATION ===");

        try {
            // Employee ID
            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();
            Validator.validateEmpID(empId);

            // Email
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Validator.validateEmail(email);

            // Phone
            System.out.print("Enter Phone Number: ");
            String phone = sc.nextLine();
            Validator.validatePhone(phone);

            // Password
            System.out.print("Create Password: ");
            String password = sc.nextLine();
            if (password.length() < 6) {
                throw new ValidationException("Password must be at least 6 characters.");
            }

            System.out.println("\nAll inputs are VALID. Registration/Login can proceed.");
        }
        catch (ValidationException ex) {
            System.out.println("\nValidation Failed:");
            System.out.println(ex.getMessage());
        }

        sc.close();
    }
}