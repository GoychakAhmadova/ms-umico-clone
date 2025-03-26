package com.example.msumico.model;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {
    private List<Long> productIDs;
    private Long userID;
}
