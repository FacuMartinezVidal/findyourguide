package com.findyourguide.api.repository;

import com.findyourguide.api.entity.Trophy.Trophy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrophyRepository extends JpaRepository<Trophy, Long> {
}
