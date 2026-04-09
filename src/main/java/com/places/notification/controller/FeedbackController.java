package com.places.notification.controller;

import com.places.notification.dto.NotificationDtos;
import com.places.notification.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Feedback", description = "User feedback form submission and retrieval")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Operation(
            summary = "Submit feedback",
            description = "Accepts a feedback form with category (select), experience (radio), recommend (checkbox) and message (text)"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationDtos.FeedbackResponse createFeedback(@Valid @RequestBody NotificationDtos.FeedbackRequest request) {
        return feedbackService.create(request);
    }

    @Operation(summary = "List all feedback entries")
    @GetMapping
    public List<NotificationDtos.FeedbackResponse> getFeedbackEntries() {
        return feedbackService.findAll();
    }
}
