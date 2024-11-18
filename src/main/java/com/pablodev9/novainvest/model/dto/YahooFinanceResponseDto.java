package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class YahooFinanceResponseDto {
    private final List<StockDto> quotes;
}
