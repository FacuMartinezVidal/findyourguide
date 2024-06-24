package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.entity.Reviews.Review;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;

import java.util.List;

public interface IReviewService {

    List<Review> findAllByUser(Long id) throws UserNotFoundException;

    Review findById(Long id) throws ServiceNotFoundException;

    void deleteById(Long id) throws ServiceNotFoundException;

}
