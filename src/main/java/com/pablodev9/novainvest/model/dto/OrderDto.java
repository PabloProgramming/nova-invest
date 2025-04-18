package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class OrderDto {
    private final Long portfolioId;
    private final Long assetId;
    private final BigDecimal quantity;
    private final OrderType orderType;
    private final BigDecimal takeProfit;
    private final BigDecimal stopLoss;
}
