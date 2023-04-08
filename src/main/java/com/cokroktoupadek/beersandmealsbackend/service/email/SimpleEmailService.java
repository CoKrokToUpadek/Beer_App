package com.cokroktoupadek.beersandmealsbackend.service.email;


import com.cokroktoupadek.beersandmealsbackend.client.config.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class SimpleEmailService {
//
//    private final Config config;
//    private final JavaMailSender javaMailSender;
//
//    public void send(String msg) {
//        log.info("Starting email preparation...");
//        try {
//            javaMailSender.send(createMailMessage(msg));
//            log.info("Email has been sent.");
//
//        } catch (MailException e) {
//            log.error("Failed to process email sending: " + e.getMessage(), e);
//        }
//    }
//
//    private SimpleMailMessage createMailMessage(String msg) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(config.getAdminMail());
//        mailMessage.setSubject("Db update Status");
//        mailMessage.setText(msg);
//        return mailMessage;
//    }
//
//}
