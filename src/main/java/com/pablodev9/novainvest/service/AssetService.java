package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.AssetNotFoundException;
import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
