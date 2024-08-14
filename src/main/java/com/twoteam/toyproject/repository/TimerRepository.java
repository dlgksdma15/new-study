package com.twoteam.toyproject.repository;

import com.twoteam.toyproject.entity.TimerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimerRepository extends JpaRepository<TimerEntity, Long> {
    List<TimerEntity> findAllByEmail(String userEmail);
}