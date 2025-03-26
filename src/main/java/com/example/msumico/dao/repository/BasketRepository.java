package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
}
