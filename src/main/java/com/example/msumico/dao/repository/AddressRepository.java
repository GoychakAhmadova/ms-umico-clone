package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
