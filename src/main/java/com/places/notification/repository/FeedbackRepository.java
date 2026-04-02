package com.places.notification.repository;

import com.places.notification.model.FeedbackEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntry, Long> {
}
