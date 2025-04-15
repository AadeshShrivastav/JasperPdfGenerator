package com.jasperexample.jasperemo.controller;

import com.jasperexample.jasperemo.service.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class PdfReportController {

    @Autowired
    private PdfReportService reportService;

    @GetMapping("/pdf-db")
    public ResponseEntity<byte[]> fromDatabase() throws Exception {
        byte[] pdf = reportService.generatePdfFromDatabase();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users-db.pdf")
                .body(pdf);
    }

    @GetMapping("/pdf-hardcoded")
    public ResponseEntity<byte[]> fromHardcoded() throws Exception {
        byte[] pdf = reportService.generatePdfFromHardcodedData();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users-hardcoded.pdf")
                .body(pdf);
    }
}

