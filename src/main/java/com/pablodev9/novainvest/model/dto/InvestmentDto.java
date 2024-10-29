package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class InvestmentDto {
    private final Long portfolioId;
    private final Long assetId;
    private final double quantity;
    private final BigDecimal purchasePrice;
    private final LocalDateTime createdAt;
}
