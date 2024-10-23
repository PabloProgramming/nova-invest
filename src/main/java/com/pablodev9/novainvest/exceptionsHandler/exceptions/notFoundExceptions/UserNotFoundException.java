package com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "User Not Found with id: ";

    public UserNotFoundException(Long userId) {
        super(DESCRIPTION + userId);
    }
}
