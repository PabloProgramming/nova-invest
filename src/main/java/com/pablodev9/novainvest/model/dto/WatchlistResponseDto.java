package com.pablodev9.novainvest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class WatchlistResponseDto {
    private final Long watchlistId;
    private final String name;
    private final List<AssetResponseDto> assetResponseDtos;
}
