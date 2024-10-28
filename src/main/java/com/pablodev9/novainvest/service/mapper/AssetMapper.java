package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.model.dto.AssetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AssetMapper {

    public List<AssetResponseDto> toSummaryDtos(final List<Asset> assets) {
        return assets.stream()
                .map(asset -> AssetResponseDto.builder()
                        .assetId(asset.getId())
                        .name(asset.getName())
                        .assetType(asset.getAssetType())
                        .currentPrice(asset.getCurrentPrice())
                        .build())
                .collect(Collectors.toList());
    }
}