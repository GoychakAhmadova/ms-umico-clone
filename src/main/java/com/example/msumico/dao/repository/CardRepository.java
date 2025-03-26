package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
