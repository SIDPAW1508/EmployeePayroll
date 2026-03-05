package com.seveneleven.employeepayroll.service;
import com.seveneleven.employeepayroll.session.Dashboard;
import com.seveneleven.employeepayroll.session.EmployeeDashboard;
import com.seveneleven.employeepayroll.session.ManagerDashboard;
public class DashboardFactory {
   public static Dashboard getDashboard(String role) {
       if ("EMPLOYEE".equals(role)) {
           return new EmployeeDashboard();
       } else if ("MANAGER".equals(role)) {
           return new ManagerDashboard();
       }
       return null;
   }
}