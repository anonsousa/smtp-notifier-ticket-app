package com.antoniosousa.smtp_notifier_ticket_app.domain.service;

import com.antoniosousa.smtp_notifier_ticket_app.domain.model.UserEntity;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailTemplateBuilder emailTemplateBuilder;
    private final JavaMailSender mailSender;

    public EmailService(EmailTemplateBuilder emailTemplateBuilder, JavaMailSender mailSender) {
        this.emailTemplateBuilder = emailTemplateBuilder;
        this.mailSender = mailSender;
    }

    public void sendEmail(UserEntity user) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String htmlContent = emailTemplateBuilder.buildWelcomeEmailContent(user.getUsername());

        helper.setTo(user.getEmail());
        helper.setSubject("Welcome to Ticketyou!");
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);

    }
}
