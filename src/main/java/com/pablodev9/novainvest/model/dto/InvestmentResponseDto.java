package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class InvestmentResponseDto {
    private final Long portfolioId;
    private final Long investmentId;
    private final Long assetId;
    private final String assetName;
    private final double quantity;
    private final BigDecimal amountInvested;
    private final BigDecimal transactionFees;
    private final BigDecimal purchasePrice;
    private final LocalDateTime createdAt;
}
