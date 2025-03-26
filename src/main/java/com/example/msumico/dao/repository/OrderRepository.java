package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
