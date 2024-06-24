package com.findyourguide.api.service;

import com.findyourguide.api.service.interfaces.IReviewService;

import lombok.RequiredArgsConstructor;

import com.findyourguide.api.dto.Review.InputReview;
import com.findyourguide.api.dto.Review.ReviewDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Reviews.Review;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.TypeNotValidException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.ReviewMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.ReviewRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final TouristRepository touristRepository;
    private final GuideRepository guideRepository;

    @Override
    public List<ReviewDTO> findAllByUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        List<Review> reviews;
        if (user instanceof Tourist) {
            reviews = ((Tourist) user).getGivenReviews();
        } else if (user instanceof Guide) {
            reviews = ((Guide) user).getReceivedReviews();
        } else {
            throw new TypeNotValidException(user.getRole().name());
        }

        return reviews.stream()
                .map(ReviewMapper::mapToReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findById(Long id) throws ServiceNotFoundException {
        return reviewRepository.findById(id)
                .map(ReviewMapper::mapToReviewDTO)
                .orElseThrow(() -> new ServiceNotFoundException());
    }

    @Override
    public ReviewDTO create(Long guideID, InputReview inputReview) throws UserNotFoundException {
        Tourist tourist = touristRepository
                .findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException());
        Guide guide = guideRepository.findById(guideID).orElseThrow(() -> new ServiceNotFoundException());

        Review review = ReviewMapper.mapToEntityFromCreateReview(tourist, guide, inputReview);
        reviewRepository.save(review);
        return ReviewMapper.mapToReviewDTO(review);
    }

    @Override
    public boolean deleteById(Long id) throws ServiceNotFoundException {
        try {
            if (!reviewRepository.existsById(id)) {
                throw new ServiceNotFoundException();
            }
            reviewRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The ID is not valid");
        }

    }
}
