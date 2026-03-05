package com.seveneleven.employeepayroll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.seveneleven.employeepayroll.auth.AuthenticationService;
import com.seveneleven.employeepayroll.exception.ValidationException;
import com.seveneleven.employeepayroll.exception.Validator;
import com.seveneleven.employeepayroll.model.DownloadToken;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
import com.seveneleven.employeepayroll.model.PayslipDownload;
import com.seveneleven.employeepayroll.model.SalaryComponents;
import com.seveneleven.employeepayroll.model.UserAccount;
import com.seveneleven.employeepayroll.service.DashboardFactory;
import com.seveneleven.employeepayroll.service.FileService;
import com.seveneleven.employeepayroll.service.PayrollService;
import com.seveneleven.employeepayroll.session.Dashboard;
import com.seveneleven.employeepayroll.session.Session;

public class EmployeeRegistrationApp {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Employee emp = registerEmployee();
        if (emp == null) return;

        Session session = authenticateUser();
        if (session == null || session.isExpired()) {
            System.out.println("Session invalid or expired. Exiting...");
            return;
        }

        Payslip payslip = generatePayslip(emp);
        if (payslip == null) return;

        downloadPayslip(payslip);

        displayDashboard(emp);

        sc.close();
    }

    // ==========================
    // UC1 - Employee Registration
    // ==========================
    private static Employee registerEmployee() {
        System.out.println("=== USE CASE 1: EMPLOYEE REGISTRATION ===");

        try {
            System.out.print("Enter Employee ID: ");
            String empID = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            // Validate input
            Validator.validateEmail(email);
            Validator.validateEmpID(empID);
            Validator.validatePhone(phone);

            UserAccount account = new UserAccount(username, password);
            Employee emp = new Employee(empID, name, email, phone, account);

            emp.persist();
            System.out.println("Employee registered successfully.");
            System.out.println(emp);

            return emp;

        } catch (ValidationException e) {
            System.out.println("Invalid details: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving info: " + e.getMessage());
        }

        return null;
    }

    // ==========================
    // UC2 - User Authentication
    // ==========================
    private static Session authenticateUser() {
        System.out.println("\n=== USE CASE 2: USER AUTHENTICATION ===");

        AuthenticationService auth = new AuthenticationService();
        auth.registerUser();

        Session session = auth.login();

        if (session != null && !session.isExpired()) {
            System.out.println("Session active and valid.");
            System.out.println(session);
            return session;
        } else {
            System.out.println("Login failed or session expired.");
            return null;
        }
    }

    // ==========================
    // UC3 - Payslip Generation
    // ==========================
    private static Payslip generatePayslip(Employee emp) {
        System.out.println("\n=== USE CASE 3: PAYSLIP GENERATION ===");

        try {
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
            sc.nextLine(); // consume leftover newline

            PayrollService service = new PayrollService();
            Payslip payslip = service.generatePayslip(emp, month, basic, hra, da, allowances);

            System.out.println(payslip);
            return payslip;

        } catch (Exception e) {
            System.out.println("Error generating payslip: " + e.getMessage());
            return null;
        }
    }

    // ==========================
    // UC4 - Payslip Download / Print
    // ==========================
    private static void downloadPayslip(Payslip original) {
        System.out.println("\n=== USE CASE 4: PAYSLIP PRINT / DOWNLOAD ===");

        try {
            String empId = original.getEmployee().getEmpId();
            String empName = original.getEmployee().getName();
            String month = original.getMonth();
            double netPay = original.getComponents().getNetPay();

            PayslipDownload download = new PayslipDownload(empId, empName, month, netPay);
            PayslipDownload cloned = (PayslipDownload) download.clone();

            if (download.equals(cloned)) {
                System.out.println("Verified: Download copy is equal to original.");
            }
            System.out.println("Original hashcode: " + download.hashCode());
            System.out.println("Cloned hashcode: " + cloned.hashCode());

            DownloadToken token = new DownloadToken();
            if (token.isExpired()) {
                System.out.println("Download token expired.");
                return;
            }

            FileService fileService = new FileService();
            String txt = fileService.savePayslipAsText(cloned);
            String pdf = fileService.savePayslipAsPdf(cloned);

            System.out.println("\nPayslip Download Successful.");
            System.out.println("Saved as text file: " + txt);
            System.out.println("Saved as PDF file: " + pdf);
            System.out.println("\n--- Printed Payslip ---");
            System.out.println(cloned);

        } catch (Exception e) {
            System.out.println("Error during payslip download: " + e.getMessage());
        }
    }

    // ==========================
    // UC5 - Dashboard Display
    // ==========================
    private static void displayDashboard(Employee emp) {
        System.out.println("\n=== USE CASE 5: DASHBOARD DISPLAY ===");

        System.out.print("Enter Role (EMPLOYEE/MANAGER): ");
        String role = sc.nextLine().trim().toUpperCase(); // normalize input

        // Create dummy payslips for demonstration
        ArrayList<Payslip> payslips = new ArrayList<>();
        SalaryComponents s1 = new SalaryComponents(30000, 2000, 1000, 1000);
        s1.setNetPay(32000);
        SalaryComponents s2 = new SalaryComponents(32000, 2000, 1000, 1000);
        s2.setNetPay(33000);
        SalaryComponents s3 = new SalaryComponents(33000, 2000, 1000, 1000);
        s3.setNetPay(34000);

        payslips.add(new Payslip(emp, s1, "Feb"));
        payslips.add(new Payslip(emp, s2, "Apr"));
        payslips.add(new Payslip(emp, s3, "May"));

        Dashboard dashboard = DashboardFactory.getDashboard(role);
        if (dashboard == null) {
            System.out.println("Invalid role. Dashboard cannot be displayed.");
            return;
        }

        dashboard.display(payslips, emp);
    }
}