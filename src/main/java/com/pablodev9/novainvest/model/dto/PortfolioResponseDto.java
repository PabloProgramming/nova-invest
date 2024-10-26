package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class PortfolioResponseDto {
    private final Long accountId;
    private final Long portfolioId;
    private final String portfolioName;
    private final BigDecimal totalValue;
    private final String updateAt;
    private final List<WatchlistResponseDto> watchlistResponseDtos;
    private final List<AssetResponseDto> assetResponseDtos;
    private final List<InvestmentSummaryDto> investmentSummaryDtos;
}
