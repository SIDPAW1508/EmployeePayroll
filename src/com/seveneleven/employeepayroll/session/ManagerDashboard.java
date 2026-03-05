package com.seveneleven.employeepayroll.session;

import java.util.ArrayList;

import java.util.Iterator;

import com.seveneleven.employeepayroll.model.Employee;

import com.seveneleven.employeepayroll.model.Payslip;

public class ManagerDashboard implements Dashboard {

    @Override

    public void display(ArrayList<Payslip> payslips, Employee employee) {

        System.out.println("\n=== MANAGER DASHBOARD ===");

        System.out.println("Manager: " + employee.getName());

        System.out.println("Dashboard Type: " + this.getClass().getName());

        double total = 0;

        Iterator<Payslip> it = payslips.iterator();

        while (it.hasNext()) {

            Payslip p = it.next();

            total += p.getComponents().getNetPay();

        }

        System.out.println("\nTeam Total YTD Earnings: " + total);

    }

}