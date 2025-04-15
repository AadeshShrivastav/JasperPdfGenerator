package com.jasperexample.jasperemo.service;

import com.jasperexample.jasperemo.entity.User;
import com.jasperexample.jasperemo.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class PdfReportService {
    @Autowired
    private UserRepository userRepository;

    public byte[] generatePdfFromDatabase() throws Exception {
        List<User> users = userRepository.findAll();

        InputStream inputStream = getClass().getResourceAsStream("/reports/sample_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generatePdfFromHardcodedData() throws Exception {
        List<User> users = List.of(
                new User(1, "Hardcoded John", "john@example.com"),
                new User(2, "Hardcoded Jane", "jane@example.com")
        );

        InputStream inputStream = getClass().getResourceAsStream("/reports/sample_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
