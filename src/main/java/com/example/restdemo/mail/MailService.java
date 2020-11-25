package com.example.restdemo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.addresses.from}")
    private String emailFromAddress;

    @Value("${spring.mail.addresses.replyTo}")
    private String emailReplyToAddress;

    public void sendEmail(String recipient, String subject, String plainText, String html) throws MailException {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(emailFromAddress);
            messageHelper.setReplyTo(emailReplyToAddress);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(plainText, html);
        };

        emailSender.send(messagePreparator);
    }
}
