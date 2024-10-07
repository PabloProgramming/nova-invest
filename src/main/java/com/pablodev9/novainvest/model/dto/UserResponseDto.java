package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class UserResponseDto {
    private final Long id;
    @NotBlank(message = "Username is required")
    private final String userName;
    @Email(message = "Email should be valid")
    private final String email;
    @NotBlank(message = "First name is required")
    private final String firstName;
    @NotBlank(message = "Last name is required")
    private final String lastName;
    @NotBlank(message = "Phone is required")
    private final String phoneNumber;
    private final UserRole role;
    private final String createdAt;
}
