package com.example.msumico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {


    private Long userId;
    private List<ProductDto> products;
    private Double totalPriceOfOrder;
    private String status;
    private LocalDateTime orderDate;
}
