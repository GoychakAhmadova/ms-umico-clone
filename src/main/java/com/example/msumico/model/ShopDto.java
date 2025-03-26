package com.example.msumico.model;

import com.example.msumico.dao.entity.CategoryEntity;
import com.example.msumico.dao.entity.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private String shopName;
    private CategoryEntity categoryId;
    private UserEntity ownerId;
}
