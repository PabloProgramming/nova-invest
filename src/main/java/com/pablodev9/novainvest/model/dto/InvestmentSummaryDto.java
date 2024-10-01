package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class InvestmentSummaryDto {
    private final Long investmentId;
    private final String assetName;
    private final BigDecimal amountInvested;
    private final BigDecimal currentValue;
    private final BigDecimal profitOrLoss;
}
