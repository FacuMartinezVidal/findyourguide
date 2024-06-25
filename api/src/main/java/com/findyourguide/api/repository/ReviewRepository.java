package com.findyourguide.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findyourguide.api.entity.Reviews.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
