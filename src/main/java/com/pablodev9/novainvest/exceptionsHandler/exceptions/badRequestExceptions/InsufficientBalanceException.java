package com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {

    private static final String DESCRIPTION = "Insufficient balance for withdrawal. Current balance: ";

    public InsufficientBalanceException(BigDecimal balance) {
        super(DESCRIPTION + balance);
    }
}
