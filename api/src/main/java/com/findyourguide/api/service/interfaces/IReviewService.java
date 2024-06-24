package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.Review.InputReview;
import com.findyourguide.api.dto.Review.ReviewDTO;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;

import java.util.List;

public interface IReviewService {

    List<ReviewDTO> findAllByUser(Long id) throws UserNotFoundException;

    ReviewDTO create(Long guideID, InputReview inputReview) throws UserNotFoundException;

    ReviewDTO findById(Long id) throws ServiceNotFoundException;

    boolean deleteById(Long id) throws ServiceNotFoundException;

}
