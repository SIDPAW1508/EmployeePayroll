package com.seveneleven.employeepayroll.service;

import java.io.FileWriter;

import java.io.IOException;

import com.seveneleven.employeepayroll.model.PayslipDownload;

public class FileService {

    public String savePayslipAsText(PayslipDownload payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmpId()

                + "_" + System.currentTimeMillis() + ".txt";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;

    }

    public String savePayslipAsPdf(PayslipDownload payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmpId()

                + "_" + System.currentTimeMillis() + ".pdf";

        FileWriter fw = new FileWriter(fileName);

        fw.write(payslip.toString());

        fw.close();

        return fileName;

    }

}
 