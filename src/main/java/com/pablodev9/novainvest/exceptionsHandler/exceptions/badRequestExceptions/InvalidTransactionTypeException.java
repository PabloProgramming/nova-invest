package com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions;

public class InvalidTransactionTypeException extends RuntimeException {

    private static final String DESCRIPTION = "Invalid transaction type: ";

    public InvalidTransactionTypeException(String transactionType) {
        super(DESCRIPTION + transactionType);
    }
}
