package com.lepreteam.leprenotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Review;
import com.lepreteam.leprenotes.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByUserId(long user_id) {
        return reviewRepository.findReviewsByUserId(user_id);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(long review_id) throws NotFoundException {
        Review review = reviewRepository.findById(review_id)
        .orElseThrow(() -> new NotFoundException(new Review()));

        reviewRepository.delete(review);
    }
}
