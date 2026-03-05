package com.seveneleven.employeepayroll.session;
import java.util.ArrayList;
import com.seveneleven.employeepayroll.model.Employee;
import com.seveneleven.employeepayroll.model.Payslip;
public interface Dashboard {
   void display(ArrayList<Payslip> payslips, Employee employee);
}