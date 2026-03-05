package com.seveneleven.employeepayroll;

import java.io.IOException;
import java.util.Scanner;

import com.seveneleven.employeepayroll.auth.AuthenticationService;
import com.seveneleven.employeepayroll.exception.ValidationException;
import com.seveneleven.employeepayroll.exception.Validator;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.model.UserAccount;
import com.seveneleven.employeepayroll.service.PayrollService;
import com.seveneleven.employeepayroll.session.Session;

/**
 * EmployeeRegistrationApp
 * 
 * Demonstrates:
 * UC1 - Employee Registration
 * UC2 - User Authentication
 * UC3 - Payslip Generation
 */
public class EmployeeRegistrationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Employee emp = null; // will store registered employee

        System.out.println("== USE CASE 1: EMPLOYEE REGISTRATION ===");

        try {

            System.out.print("Enter Employee ID: ");
            String empID = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter UserName: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            // Validate input
            Validator.validateEmail(email);
            Validator.validateEmpID(empID);
            Validator.validatePhone(phone);

            // Create user account
            UserAccount account = new UserAccount(username, password);

            // Create employee
            emp = new Employee(empID, name, email, phone, account);

            // Save employee to file
            emp.persist();

            System.out.println("Employee registered successfully");
            System.out.println(emp);

        } 
        catch (ValidationException e) {
            System.out.println("\nInvalid details: " + e.getMessage());
        } 
        catch (IOException e) {
            System.out.println("\nError saving info");
        }

        // ==========================
        // USE CASE 2 - AUTHENTICATION
        // ==========================
        System.out.println("\n=== USE CASE 2: USER AUTHENTICATION ===");

        AuthenticationService auth = new AuthenticationService();

        auth.registerUser();

        Session session = auth.login();

        if (session != null) {

            System.out.println("\n" + session);

            if (!session.isExpired()) {
                System.out.println("Session active and valid.");
            }
        }

        // ==========================
        // USE CASE 3 - PAYSLIP
        // ==========================
        System.out.println("\n=== USE CASE 3: PAYSLIP GENERATION ===");

        System.out.print("Enter Month: ");
        String month = sc.nextLine();

        System.out.print("Enter Basic Salary: ");
        double basic = sc.nextDouble();

        System.out.print("Enter HRA: ");
        double hra = sc.nextDouble();

        System.out.print("Enter DA: ");
        double da = sc.nextDouble();

        System.out.print("Enter Allowances: ");
        double allowances = sc.nextDouble();

        PayrollService service = new PayrollService();

        Payslip payslip = service.generatePayslip(emp, month, basic, hra, da, allowances);

        System.out.println(payslip);

        sc.close();
    }
}