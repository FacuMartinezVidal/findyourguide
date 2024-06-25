package com.findyourguide.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findyourguide.api.entity.Service.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByGuideId(Long guideId);
}
