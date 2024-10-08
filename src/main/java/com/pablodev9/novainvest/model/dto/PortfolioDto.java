package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class PortfolioDto {
    private final Long accountId;
    private final Long portfolioId;
    private final String portfolioName;
    private final BigDecimal totalValue;
    private final String createdAt;
}
