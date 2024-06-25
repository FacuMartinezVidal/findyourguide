package com.findyourguide.api.repository;

import com.findyourguide.api.entity.Trophy.Trophy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrophyRepository extends JpaRepository<Trophy, Long> {
    Optional<List<Trophy>> findByUserId(Long userId);
}
