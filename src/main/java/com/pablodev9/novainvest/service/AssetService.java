package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.AssetNotFoundException;
import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @SneakyThrows
    public Asset findAssetById(final Long assetId) {
        final Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        if (optionalAsset.isPresent()) {
            return optionalAsset.get();
        }
        throw new AssetNotFoundException(assetId);
    }

    public List<Asset> findAssetById(final List<Long> assetIds) {
        List<Asset> assets = assetRepository.findAllById(assetIds);

        List<Long> missingAssetIds = assetIds.stream()
                .filter(id -> assets.stream().noneMatch(asset -> asset.getId().equals(id)))
                .collect(Collectors.toList());

        if (!missingAssetIds.isEmpty()) {
            throw new AssetNotFoundException(missingAssetIds);
        }
        return assets;
    }
}
