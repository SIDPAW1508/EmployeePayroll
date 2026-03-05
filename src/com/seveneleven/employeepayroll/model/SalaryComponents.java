package com.seveneleven.employeepayroll.model;

public class SalaryComponents {

    private double basicSalary;
    private double hra;
    private double da;
    private double allowances;

    private double pf;
    private double tax;
    private double netPay;

    public SalaryComponents(double basicSalary, double hra, double da, double allowances) {
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.allowances = allowances;
    }

    // setters
    public void setPf(double pf) {
        this.pf = pf;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    // getters
    public double getPf() {
        return pf;
    }

    public double getTax() {
        return tax;
    }

    public double getNetPay() {
        return netPay;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHra() {
        return hra;
    }

    public double getDa() {
        return da;
    }

    public double getAllowances() {
        return allowances;
    }
}