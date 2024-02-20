package com.example.ems_thymeleaf.service;

import com.example.ems_thymeleaf.entity.Mail;

public interface EmailService {
    String getMailSendFrom();

    void sendMail(Mail mail);
}
