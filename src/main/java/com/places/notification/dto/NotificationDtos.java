package com.places.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NotificationDtos {

    public record FeedbackRequest(
            @NotBlank String category,
            @NotBlank String experience,
            boolean recommend,
            @NotBlank String message
    ) {
    }

    public record FeedbackResponse(
            Long id,
            String category,
            String experience,
            boolean recommend,
            String message
    ) {
    }

    public record SendEmailRequest(
            @Email @NotBlank String to,
            @NotBlank String subject,
            @NotBlank String body
    ) {
    }

    public record EmailResponse(String status, String recipient, String subject) {
    }
}
