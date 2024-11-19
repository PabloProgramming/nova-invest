package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderFinancialOperationService {

    public BigDecimal calculateAmountInvested(Order order) {
        return order.getPurchasePrice()
                .multiply(order.getQuantity())
                .add(order.getTransactionFees());
    }

    public BigDecimal calculateProfitOrLoss(Order order) {
        BigDecimal initialInvestmentCost = order.getPurchasePrice()
                .multiply(order.getQuantity())
                .add(order.getTransactionFees());
        BigDecimal currentValue = order.getPrice()
                .multiply(order.getQuantity());
        return currentValue.subtract(initialInvestmentCost);
    }

    private static final BigDecimal TRANSACTION_FEE_RATE = new BigDecimal("0.02");

    public BigDecimal calculateTransactionFees(BigDecimal purchasePrice, BigDecimal quantity) {
        return purchasePrice.multiply(quantity).multiply(TRANSACTION_FEE_RATE);
    }

    public BigDecimal calculateCurrentValue(final Order order) {
        BigDecimal amountInvested = order.getAmountInvested();
        BigDecimal transactionFees = order.getTransactionFees();
        BigDecimal profitOrLoss = calculateProfitOrLoss(order);

        return amountInvested.subtract(transactionFees).add(profitOrLoss);
    }
}
