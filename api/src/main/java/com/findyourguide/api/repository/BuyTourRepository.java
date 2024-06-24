package com.findyourguide.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

public interface BuyTourRepository extends JpaRepository<PurchasedService, Long> {

}
