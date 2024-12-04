package com.samuel.demoEmail1.service;


import com.samuel.demoEmail1.payload.EmailDetails;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class SendEmailService {

    private final EmailService emailService;

    public SendEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


    public String emailSent() throws MessagingException, IOException {

        EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient("samuelgbenga972@gmail.com");
                emailDetails.setFullName("Samuel G. Joseph");
                emailDetails.setSubject("Merry Xmas");
                emailDetails.setEvent("Happy Christmas");


        emailService.sendSimpleMailMessage(emailDetails);

        return "Email has been sent";
    }


}
