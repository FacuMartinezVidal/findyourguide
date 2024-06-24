package com.findyourguide.api.repository;

import com.findyourguide.api.entity.PurchasedService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyTourRepository extends JpaRepository<PurchasedService, Long> {

}
