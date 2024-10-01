package com.pablodev9.novainvest.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class UserPortfolioDto {
    private final Long id;
    @NotBlank(message = "Username is required")
    private final String userName;
    @Email(message = "Email should be valid")
    private final String email;
    private final BigDecimal balance;
    private final List<PortfolioSummaryDto> portfolioSummaryDtos;
}
