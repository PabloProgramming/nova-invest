package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class PortfolioSummaryDto {
    private final Long id;
    private final String portfolioName;
    private final BigDecimal totalValue;
    private final List<InvestmentSummaryDto> investmentSummaryDtos;
}
