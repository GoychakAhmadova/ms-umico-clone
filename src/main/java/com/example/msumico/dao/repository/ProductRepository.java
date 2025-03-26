package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
