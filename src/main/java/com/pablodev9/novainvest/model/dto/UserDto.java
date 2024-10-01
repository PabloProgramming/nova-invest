package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class UserDto {
    @NotBlank(message = "Username is required")
    private final String userName;
    @Email(message = "Email should be valid")
    private final String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private final String password;
    @NotBlank(message = "First name is required")
    private final String firstName;
    @NotBlank(message = "Last name is required")
    private final String lastName;
    private final BigDecimal balance;
    private final UserRole role;
}