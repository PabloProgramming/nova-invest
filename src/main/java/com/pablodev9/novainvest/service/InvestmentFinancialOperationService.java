package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class InvestmentFinancialOperationService {

    public BigDecimal calculateAmountInvested(Investment investment) {
        if (investment == null || investment.getQuantity() == null || investment.getPurchasePrice() == null) {
            return BigDecimal.ZERO;
        }
        return investment.getQuantity().multiply(investment.getPurchasePrice());
    }

    public BigDecimal calculateProfitOrLoss(final Investment investment) {
        BigDecimal initialInvestmentCost = investment.getPurchasePrice()
                .multiply(investment.getQuantity())
                .add(investment.getTransactionFees());
        BigDecimal currentValue = investment.getAsset().getCurrentPrice()
                .multiply(investment.getQuantity());
        return currentValue.subtract(initialInvestmentCost);
    }

    private static final BigDecimal TRANSACTION_FEE_RATE = new BigDecimal("0.01");

    public BigDecimal calculateTransactionFees(BigDecimal currentPrice, BigDecimal quantity) {
        return currentPrice.multiply(quantity).multiply(TRANSACTION_FEE_RATE);
    }
}
