package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
