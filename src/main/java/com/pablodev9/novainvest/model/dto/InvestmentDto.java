package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class InvestmentDto {
    private final Long portfolioId;
    private final Long assetId;
    private final BigDecimal quantity;
}
