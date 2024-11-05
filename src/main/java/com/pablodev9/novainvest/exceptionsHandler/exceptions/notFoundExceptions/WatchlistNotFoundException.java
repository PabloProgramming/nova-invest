package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class WatchlistNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Watchlist Not Found with id: ";

    public WatchlistNotFoundException(Long watchlistId) {
        super(DESCRIPTION + watchlistId);
    }
}
