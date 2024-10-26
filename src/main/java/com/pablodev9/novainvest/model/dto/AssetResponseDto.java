package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.AssetType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class AssetResponseDto {
    private final Long assetId;
    private final String name;
    private final AssetType assetType;
    private final BigDecimal currentPrice;

}
