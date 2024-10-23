package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UtilitiesService {

    private final InvestmentService investmentService;

    public BigDecimal calculateTotalValue(Portfolio portfolio) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Investment investment : portfolio.getInvestments()) {
            if (Objects.nonNull(investment)) {
                BigDecimal amountInvested = investmentService.calculateAmountInvested(investment);
                BigDecimal investmentValue = investmentService.calculateProfitOrLoss(investment);
                totalValue = totalValue.add(amountInvested).add(investmentValue);
            }
        }
        return totalValue;
    }
}
