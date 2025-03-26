package com.example.msumico.model;

import com.example.msumico.dao.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Size(min = 3, max = 20)
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Size(min = 3, max = 40)
    @NotBlank(message = "Surname cannot be empty")
    private String surname;
    private Gender gender;
    @Pattern(regexp = "^[0-9]{9}$", message = "Phone number must be exactly 9 digits")
    @NotBlank(message = "Phone number cannot be null")
    private String phoneNumber;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Birthday cannot be empty")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
    @Size(min = 7, max = 7, message = "FIN must be exactly 7 characters")
    @NotBlank(message = "FIN cannot be empty")
    @Pattern(regexp = "^[A-Z0-9]{7}$", message = "FIN must contain only uppercase letters and digits")
    private String fin;
    @PositiveOrZero(message = "Balance cannot be negative")
    private Double balance;
    private List<CardDto> cards;

}
