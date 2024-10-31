package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FinancialOperationService {


    public BigDecimal calculateTotalValue(Portfolio portfolio) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Investment investment : portfolio.getInvestments()) {
            if (Objects.nonNull(investment)) {
                BigDecimal amountInvested = calculateAmountInvested(investment);
                BigDecimal investmentValue = calculateProfitOrLoss(investment);
                totalValue = totalValue.add(amountInvested).add(investmentValue);
            }
        }
        return totalValue;
    }

    public BigDecimal calculateAmountInvested(final Investment investment) {
        return investment.getPurchasePrice()
                .multiply(investment.getQuantity())
                .add(investment.getTransactionFees());
    }

    public BigDecimal calculateProfitOrLoss(final Investment investment) {
        BigDecimal initialInvestmentCost = investment.getPurchasePrice()
                .multiply(investment.getQuantity())
                .add(investment.getTransactionFees());
        BigDecimal currentValue = investment.getCurrentPrice()
                .multiply(investment.getQuantity());
        return currentValue.subtract(initialInvestmentCost);
    }

    private static final BigDecimal TRANSACTION_FEE_RATE = new BigDecimal("0.01");

    public BigDecimal calculateTransactionFees(BigDecimal currentPrice, BigDecimal quantity) {
        return currentPrice.multiply(quantity).multiply(TRANSACTION_FEE_RATE);
    }
}
