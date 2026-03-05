package com.seveneleven.employeepayroll.session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
public class EmployeeDashboard implements Dashboard {
   @Override
   public void display(ArrayList<Payslip> payslips, Employee employee) {
       System.out.println("\n=== EMPLOYEE DASHBOARD ===");
       System.out.println("Welcome, " + employee.getName());
       System.out.println("Dashboard Type: " + this.getClass().getName());
       // sort by net pay descending
       Collections.sort(payslips, new Comparator<Payslip>() {
           public int compare(Payslip p1, Payslip p2) {
               return (int)(p2.getComponents().getNetPay() - p1.getComponents().getNetPay());
           }
       });
       System.out.println("\nRecent Payslips (Top 3):");
       int count = 0;
       Iterator<Payslip> it = payslips.iterator();
       while (it.hasNext() && count < 3) {
           Payslip p = it.next();
           System.out.println(p);
           count++;
       }
       double total = 0;
       Iterator<Payslip> it2 = payslips.iterator();
       while (it2.hasNext()) {
           Payslip p = it2.next();
           total += p.getComponents().getNetPay();
       }
       System.out.println("\nYear-To-Date Earnings: " + total);
   }
}