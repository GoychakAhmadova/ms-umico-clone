package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.PaymentEntity;
import com.example.msumico.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
