package com.places.notification.service;

import com.places.notification.dto.NotificationDtos;
import com.places.notification.model.FeedbackEntry;
import com.places.notification.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public NotificationDtos.FeedbackResponse create(NotificationDtos.FeedbackRequest request) {
        FeedbackEntry entry = new FeedbackEntry();
        entry.setCategory(request.category());
        entry.setExperience(request.experience());
        entry.setRecommend(request.recommend());
        entry.setMessage(request.message());

        FeedbackEntry saved = feedbackRepository.save(entry);
        return new NotificationDtos.FeedbackResponse(
                saved.getId(),
                saved.getCategory(),
                saved.getExperience(),
                saved.getRecommend(),
                saved.getMessage()
        );
    }

    public List<NotificationDtos.FeedbackResponse> findAll() {
        return feedbackRepository.findAll().stream()
                .map(entry -> new NotificationDtos.FeedbackResponse(
                        entry.getId(),
                        entry.getCategory(),
                        entry.getExperience(),
                        entry.getRecommend(),
                        entry.getMessage()
                )).toList();
    }
}
