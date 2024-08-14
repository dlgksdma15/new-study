package com.twoteam.toyproject.repository;

import com.twoteam.toyproject.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoEntity,Integer> {
    List<ToDoEntity> findAllByEmail(String userEmail);
}