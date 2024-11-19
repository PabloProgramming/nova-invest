package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class StockDto {
    private final String symbol;
    private final BigDecimal price;
    private final BigDecimal change;
    private final BigDecimal changeInPercent;
    private final BigDecimal marketCap;
    private final BigDecimal dayHigh;
    private final BigDecimal dayLow;
    private final BigDecimal yearHigh;
    private final BigDecimal yearLow;
    private final Long volume;
    private final Long avgVolume;
}
