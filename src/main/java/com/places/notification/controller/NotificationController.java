package com.places.notification.controller;

import com.places.notification.dto.NotificationDtos;
import com.places.notification.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notifications", description = "Email notification dispatch via MailTrap")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(
            summary = "Send an email notification",
            description = "Dispatches an email via MailTrap and records it in the audit log"
    )
    @PostMapping("/email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NotificationDtos.EmailResponse sendMail(@Valid @RequestBody NotificationDtos.SendEmailRequest request) {
        return emailService.send(request);
    }
}
