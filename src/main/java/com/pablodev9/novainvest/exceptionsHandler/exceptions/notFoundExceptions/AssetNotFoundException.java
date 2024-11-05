package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

import java.util.List;

public class AssetNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Asset Not Found with id: ";
    private static final String DESCRIPTIONS = "Assets Not Found with ids: ";


    public AssetNotFoundException(Long assetId) {
        super(DESCRIPTION + assetId);
    }

    public AssetNotFoundException(List<Long> assetIds) {
        super(DESCRIPTIONS + assetIds);
    }
}
