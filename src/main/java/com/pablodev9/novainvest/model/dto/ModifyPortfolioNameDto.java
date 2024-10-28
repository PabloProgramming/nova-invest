package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class ModifyPortfolioNameDto {
    private final Long portfolioId;
    private final String portfolioName;
    private final LocalDateTime updatedAt;
}
