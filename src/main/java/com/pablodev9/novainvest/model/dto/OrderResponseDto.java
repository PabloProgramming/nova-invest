package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class OrderResponseDto {
    private final Long portfolioId;
    private final Long orderId;
    private final OrderType orderType;
    private final Long assetId;
    private final String assetName;
    private final BigDecimal quantity;
    private final BigDecimal amountInvested;
    private final BigDecimal takeProfit;
    private final BigDecimal stopLoss;
    private final BigDecimal transactionFees;
    private final BigDecimal purchasePrice;
    private final BigDecimal currentValue;
    private final BigDecimal profitOrLoss;
    private final LocalDateTime createdAt;
}
