package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class PortfolioNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Portfolio Not Found with id: ";

    public PortfolioNotFoundException(Long portfolioId) {
        super(DESCRIPTION + portfolioId);
    }
}
