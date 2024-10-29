package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class AssetNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Asset Not Found with id: ";

    public AssetNotFoundException(Long assetId) {
        super(DESCRIPTION + assetId);
    }
}
