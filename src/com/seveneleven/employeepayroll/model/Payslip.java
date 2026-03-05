package com.seveneleven.employeepayroll.model;

/**
 * Payslip
 *
 * Represents a salary statement for an employee
 * containing earnings, deductions, and net pay.
 */
public class Payslip {

    // Employee details
    private Employee employee;

    // Salary components (earnings + deductions)
    private SalaryComponents components;

    // Salary month
    private String month;

    /**
     * Constructor to initialize payslip data
     */
    public Payslip(Employee employee, SalaryComponents components, String month) {
        this.employee = employee;
        this.components = components;
        this.month = month;
    }

    /**
     * Returns formatted payslip details
     */
    @Override
    public String toString() {

        return "\n========== PAYSLIP ==========\n"
                + "Month : " + month + "\n"
                + "Employee ID : " + employee.getEmpId() + "\n"
                + "Employee Name : " + employee.getName() + "\n\n"

                + "---- Earnings ----\n"
                + "Basic Salary : " + components.getBasicSalary() + "\n"
                + "HRA : " + components.getHra() + "\n"
                + "DA : " + components.getDa() + "\n"
                + "Allowances : " + components.getAllowances() + "\n\n"

                + "---- Deductions ----\n"
                + "PF : " + components.getPf() + "\n"
                + "Tax : " + components.getTax() + "\n\n"

                + "Net Pay : " + components.getNetPay() + "\n"

                + "==============================";
    }

	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}
	public SalaryComponents getComponents() {
		return components;
	}
	public String getMonth() {
		return month;
	}
}