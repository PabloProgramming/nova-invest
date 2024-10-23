package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class InvestmentService {

    public BigDecimal calculateAmountInvested(Investment investment) {
        return investment.getPurchasePrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()))
                .add(investment.getTransactionFees());
    }

    public BigDecimal calculateProfitOrLoss(Investment investment) {
        BigDecimal initialInvestmentCost = investment.getPurchasePrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()))
                .add(investment.getTransactionFees());
        BigDecimal currentValue = investment.getCurrentPrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()));
        return currentValue.subtract(initialInvestmentCost);
    }
}
