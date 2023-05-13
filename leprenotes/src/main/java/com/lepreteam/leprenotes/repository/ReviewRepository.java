package com.lepreteam.leprenotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lepreteam.leprenotes.domain.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    public List<Review> findAll();

    @Query( value = "SELECT * FROM reviews WHERE user_id=?", nativeQuery = true)
    List<Review> findReviewsByUserId(long user_id);
}
