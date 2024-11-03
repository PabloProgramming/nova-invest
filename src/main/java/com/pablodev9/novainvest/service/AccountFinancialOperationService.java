package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AccountFinancialOperationService {

    private final PortfolioFinancialOperationService portfolioFinancialOperationService;

    private final InvestmentFinancialOperationService investmentFinancialOperationService;

    public BigDecimal calculateEquity(Account account) {
        BigDecimal balance = calculateBalance(account); // Get the balance

        BigDecimal margin = calculateMargin(account);
        return balance.subtract(margin);
    }

    public BigDecimal calculateBalance(Account account) {
        BigDecimal previousBalance = account.getBalance();
        BigDecimal totalPortfolioValue = account.getPortfolios().stream()
                .filter(Objects::nonNull)
                .map(portfolioFinancialOperationService::calculatePortfolioValue) // Calculate total value of portfolios
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal margin = calculateMargin(account);
        return previousBalance.add(totalPortfolioValue).subtract(margin);
    }

    public BigDecimal calculateMargin(Account account) {
        return account.getPortfolios().stream()
                .filter(Objects::nonNull)
                .flatMap(portfolio -> portfolio.getInvestments().stream()) // Flatten the investments into a single stream
                .map(investmentFinancialOperationService::calculateAmountInvested) // Map each investment to its invested amount (including fees)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all amounts invested, starting from BigDecimal.ZERO
    }

    public BigDecimal calculateReservedFunds(Account account) {
        return account.getPortfolios().stream()
                .filter(Objects::nonNull)
                .flatMap(portfolio -> portfolio.getOrders().stream()) // Flatten the orders into a single stream
                .filter(order -> order.getOrderStatus().equals(OrderStatus.PENDING)) // Only pending orders
                .map(order -> order.getPrice().multiply(order.getQuantity())) // Calculate reserved funds for each order
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all reserved funds, starting from BigDecimal.ZERO
    }

}