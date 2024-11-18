package com.pablodev9.novainvest.exceptionsHandler;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions.InsufficientBalanceException;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions.InvalidTransactionTypeException;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, AccountNotFoundException.class,
            PortfolioNotFoundException.class, AssetNotFoundException.class,
            InvestmentNotFoundException.class, StockNotFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTransactionTypeException.class, InsufficientBalanceException.class})
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }
}
