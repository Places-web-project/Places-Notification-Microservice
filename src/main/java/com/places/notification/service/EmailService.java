package com.places.notification.service;

import com.places.notification.dto.NotificationDtos;
import com.places.notification.model.EmailLog;
import com.places.notification.repository.EmailLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailLogRepository emailLogRepository;

    @Value("${notification.from}")
    private String from;

    public EmailService(JavaMailSender mailSender, EmailLogRepository emailLogRepository) {
        this.mailSender = mailSender;
        this.emailLogRepository = emailLogRepository;
    }

    public NotificationDtos.EmailResponse send(NotificationDtos.SendEmailRequest request) {
        EmailLog log = new EmailLog();
        log.setRecipient(request.to());
        log.setSubject(request.subject());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(request.to());
            message.setSubject(request.subject());
            message.setText(request.body());
            mailSender.send(message);
            log.setStatus("SENT");
        } catch (Exception ex) {
            log.setStatus("FAILED: " + ex.getMessage());
            emailLogRepository.save(log);
            throw ex;
        }

        emailLogRepository.save(log);
        return new NotificationDtos.EmailResponse("SENT", request.to(), request.subject());
    }
}
