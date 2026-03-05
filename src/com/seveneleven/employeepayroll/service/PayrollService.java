package com.seveneleven.employeepayroll.service;

import com.seveneleven.employeepayroll.model.SalaryComponents;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;

/**
 * PayrollService
 *
 * Responsible for payroll calculations and generating a payslip
 * for a specific employee and month.
 */
public class PayrollService {

    /**
     * Generates a payslip for the given employee.
     *
     * @param employee   Employee object
     * @param month      Salary month
     * @param basic      Basic salary
     * @param hra        House Rent Allowance
     * @param da         Dearness Allowance
     * @param allowances Additional allowances
     * @return Payslip object containing salary breakdown
     */
    public Payslip generatePayslip(Employee employee, String month,
                                   double basic, double hra, double da, double allowances) {

        // Create salary component object
        SalaryComponents sc = new SalaryComponents(basic, hra, da, allowances);

        // Calculate gross salary
        double gross = basic + hra + da + allowances;

        sc.setPf(basic * 0.12);
        sc.setTax(gross * 0.10);
        sc.setNetPay(gross - (sc.getPf() + sc.getTax()));
        // Calculate net salary
        sc.setNetPay(gross - (sc.getPf() + sc.getTax()));

        // Return generated payslip
        return new Payslip(employee, sc, month);
    }
}