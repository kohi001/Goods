package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.entity.Employee;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
        void exportPdf(HttpServletResponse response, Employee employee);
}
