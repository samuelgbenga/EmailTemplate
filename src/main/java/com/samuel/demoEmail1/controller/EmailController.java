package com.samuel.demoEmail1.controller;


import com.samuel.demoEmail1.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class EmailController {


    private final SendEmailService sendEmailService;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @GetMapping
    public ResponseEntity<String> sendEmail(){
        try {
            return ResponseEntity.ok(sendEmailService.emailSent());
        }
        catch (MessagingException | IOException e){
            throw new RuntimeException("Email error: " + e.getMessage());
        }

    }
}
