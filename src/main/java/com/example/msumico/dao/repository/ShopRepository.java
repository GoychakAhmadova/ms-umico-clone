package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
}
