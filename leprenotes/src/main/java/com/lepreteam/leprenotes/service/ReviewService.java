package com.lepreteam.leprenotes.service;

import java.util.List;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Review;

public interface ReviewService {
    List<Review> findAll();
    List<Review> getReviewsByUserId(long user_id);
    Review addReview(Review review);
    void deleteReview(long review_id) throws NotFoundException;
}
