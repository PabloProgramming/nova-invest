package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class AccountNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Account Not Found with id: ";

    public AccountNotFoundException(Long accountId) {
        super(DESCRIPTION + accountId);
    }
}
