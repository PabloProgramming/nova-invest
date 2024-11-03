package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class InvestmentNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Investment Not Found with id: ";

    public InvestmentNotFoundException(Long investmentId) {
        super(DESCRIPTION + investmentId);
    }
}
