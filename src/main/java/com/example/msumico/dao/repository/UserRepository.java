package com.example.msumico.dao.repository;

import com.example.msumico.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
