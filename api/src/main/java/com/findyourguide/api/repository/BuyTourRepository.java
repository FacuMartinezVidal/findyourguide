package com.findyourguide.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

public interface BuyTourRepository extends JpaRepository<PurchasedService, Long> {
    List<PurchasedService> findByServiceId(Long serviceId);
}
