package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.OrderStatus;
import com.pablodev9.novainvest.model.enums.OrderType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class OrderDto {
    private final Long orderId;
    private final String assetName;
    private final BigDecimal amountInvested;
    private final BigDecimal profitOrLoss;
    private final OrderStatus orderStatus;
    private final OrderType orderType;
}
