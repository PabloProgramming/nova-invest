package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class StockNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Stock Not Found with symbol: ";

    public StockNotFoundException(String symbol) {
        super(DESCRIPTION + symbol);
    }
}
