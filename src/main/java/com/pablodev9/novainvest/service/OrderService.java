package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderService {

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
}
