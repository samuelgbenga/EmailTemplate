package com.samuel.demoEmail1.service;

import com.samuel.demoEmail1.payload.EmailDetails;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Service

public class EmailService{


    @Value("${spring.mail.username}")
    private String senderEmail;

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine tEngine;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine tEngine) {
        this.javaMailSender = javaMailSender;
        this.tEngine = tEngine;
    }


    public void sendSimpleMailMessage(EmailDetails emailDetails) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        Map<String, Object> variables = Map.of(
                "name", emailDetails.getEvent(),
                "event", emailDetails.getEvent()
        );
        context.setVariables(variables);
        helper.setFrom(senderEmail);
        helper.setTo(emailDetails.getRecipient());
        helper.setSubject(emailDetails.getSubject());
        String html = tEngine.process("email", context);
        helper.setText(html, true);

        // Add an attachment
//        File attachment = new File("/path/to/your/attachment.pdf"); // Specify the file path
//        if (attachment.exists()) {
//            helper.addAttachment("attachment.pdf", attachment); // Name the attachment as it should appear in the email
//        }

        // Load the file from the resources folder
        Resource resource = new ClassPathResource("static/xmas.jpg"); // Path relative to src/main/resources
        if (resource.exists()) {
            File file = resource.getFile();
            helper.addAttachment("xmas.jpg", file); // Name the attachment as it should appear in the email
        }


        javaMailSender.send(msg);




    }

}
