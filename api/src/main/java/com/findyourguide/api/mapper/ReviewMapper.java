package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.Review.InputReview;
import com.findyourguide.api.dto.Review.ReviewDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.Reviews.Review;

public class ReviewMapper {

    public static ReviewDTO mapToReviewDTO(Review review) {
        if (review == null) {
            return null;
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setFrom(TouristMapper.mapToTouristDTO(review.getTourist(), false, false));
        reviewDTO.setTo(GuideMapper.mapToGuideDTO(review.getGuide(), false));
        reviewDTO.setScore(review.getScore());
        reviewDTO.setDescription(review.getDescription());
        reviewDTO.setDate(review.getDate());
        return reviewDTO;
    }

    public static Review mapToEntityFromCreateReview(Tourist tourist, Guide guide, InputReview inputReview) {
        Review review = new Review();
        review.setTourist(tourist);
        review.setGuide(guide);
        review.setScore(inputReview.getScore());
        review.setDate(inputReview.getDate());
        review.setDescription(inputReview.getDescription());
        return review;
    }
}
