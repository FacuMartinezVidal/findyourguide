package com.findyourguide.api.service;

import com.findyourguide.api.service.interfaces.IReviewService;

import lombok.RequiredArgsConstructor;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.Reviews.Review;
import com.findyourguide.api.entity.Service.Service;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.BuyTourMapper;
import com.findyourguide.api.repository.BuyTourRepository;
import com.findyourguide.api.repository.ReviewRepository;
import com.findyourguide.api.repository.ServiceRepository;
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

    @Override
    public List<Review> findAllByUser(Long id) throws UserNotFoundException {
        // return userRepository.findById(id)
        // .map(tourist -> tourist.getPurchasedService().stream()
        // .map(BuyTourMapper::mapToBuyTourDTO)
        // .collect(Collectors.toList()))
        // .orElseThrow(() -> new UserNotFoundException());
        return null;
    }

    @Override
    public Review findById(Long id) throws ServiceNotFoundException {
        // return buyTourRepository.findById(id)
        // .map(BuyTourMapper::mapToBuyTourDTO)
        // .orElseThrow(() -> new ServiceNotFoundException());
        return null;

    }

    @Override
    public void deleteById(Long id) throws ServiceNotFoundException {
        // if (!buyTourRepository.existsById(id)) {
        // throw new ServiceNotFoundException();
        // }
        // buyTourRepository.deleteById(id);
        return;

    }
}
