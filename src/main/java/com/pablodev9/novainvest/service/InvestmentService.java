package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.repository.InvestmentRepository;
import com.pablodev9.novainvest.service.mapper.InvestmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class InvestmentService {

    private final InvestmentMapper investmentMapper;

    private final InvestmentRepository investmentRepository;

    public BigDecimal calculateAmountInvested(final Investment investment) {
        return investment.getPurchasePrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()))
                .add(investment.getTransactionFees());
    }

    public BigDecimal calculateProfitOrLoss(final Investment investment) {
        BigDecimal initialInvestmentCost = investment.getPurchasePrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()))
                .add(investment.getTransactionFees());
        BigDecimal currentValue = investment.getCurrentPrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()));
        return currentValue.subtract(initialInvestmentCost);
    }

    public InvestmentResponseDto addInvestmentToPortfolio(final InvestmentDto investmentDto) {
        final Investment investment = investmentMapper.dtoToInvestment(investmentDto);
        final Investment savedInvestment = investmentRepository.save(investment);
        return investmentMapper.investmentToDto(savedInvestment);
    }
}
