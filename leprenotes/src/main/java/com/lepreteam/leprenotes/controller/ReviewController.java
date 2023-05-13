package com.lepreteam.leprenotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.FieldError;

import com.exceptions.ErrorMessage;
import com.exceptions.NotFoundException;
import com.lepreteam.leprenotes.domain.Review;
import com.lepreteam.leprenotes.service.ReviewService;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews() {
        logger.info("Begin get reviews");
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/reviews/{user_id}/users")
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable long user_id) throws NotFoundException {
        logger.info("Begin get reviews by user Id");
        return new ResponseEntity<>(reviewService.getReviewsByUserId(user_id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody Review review) throws NotFoundException {
        logger.info("Begin post reviews");
        Review newReview = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws NotFoundException {
        logger.info("Begin delete reviews");
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException nfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, nfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
