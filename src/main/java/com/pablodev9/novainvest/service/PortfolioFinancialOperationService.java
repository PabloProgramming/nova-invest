package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PortfolioFinancialOperationService {

    private final InvestmentFinancialOperationService investmentFinancialOperationService;

    public BigDecimal calculatePortfolioValue(Portfolio portfolio) {
        return portfolio.getInvestments().stream()
                .filter(Objects::nonNull)
                .map(investmentFinancialOperationService::calculateCurrentValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
