package com.example.msumico.model;

import com.example.msumico.dao.entity.ShopEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotBlank(message = "Product name cannot be empty")
    private String productName;
    @Positive(message = "Price cannot be negative or zero")
    private Double productPrice;
    @PositiveOrZero(message = "Stock quantity cannot be negative")
    private Long stockQuantity;
    private Long shopId;
    private Long categoryId;


}
