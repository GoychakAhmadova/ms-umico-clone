package com.example.msumico.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @Size(min = 16, max = 16)
    @Pattern(regexp = "^(4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$",
            message = "Invalid PAN format. Must be a valid Visa or MasterCard number.")
    private String pan;
    @Size(min = 3, max = 3)
    private String cvv;
    @FutureOrPresent
    private LocalDate expiryDate;
    @Setter(AccessLevel.NONE)
    @PositiveOrZero
    private Double balance;
    private Long userId;

}
