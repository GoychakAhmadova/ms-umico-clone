package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.BasketItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BasketItemRepository extends JpaRepository<BasketItemEntity, Long> {
}
