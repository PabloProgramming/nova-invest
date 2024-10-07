package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class AccountPortfolioDto {
    private final Long id;
    private final BigDecimal balance;
    private final BigDecimal equity;
    private final BigDecimal margin;
    private final BigDecimal reservedFunds;
    private final String updatedAt;
    private final List<PortfolioSummaryDto> portfolioSummaryDtos;
}
