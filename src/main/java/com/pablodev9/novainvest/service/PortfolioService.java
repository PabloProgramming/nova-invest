package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.repository.PortfolioRepository;
import com.pablodev9.novainvest.service.mapper.PortfolioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private InvestmentService investmentService;

    public PortfolioDto createPortfolio(final PortfolioDto portfolioDto) {
        final Portfolio portfolio = portfolioMapper.dtoToPortfolio(portfolioDto);
        final Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.portfolioToDto(savedPortfolio);
    }

    public BigDecimal calculateTotalValue(Portfolio portfolio) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Investment investment : portfolio.getInvestments()) {
            if (investment != null) {
                BigDecimal amountInvested = (investmentService.calculateAmountInvested(investment) != null ? investmentService.calculateAmountInvested(investment) : BigDecimal.ZERO);
                BigDecimal investmentValue = investmentService.calculateProfitOrLoss(investment);
                totalValue = totalValue.add(amountInvested).add(investmentValue);
            }
        }
        return totalValue;
    }
}
