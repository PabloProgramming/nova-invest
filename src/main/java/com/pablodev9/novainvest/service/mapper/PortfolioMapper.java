package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PortfolioMapper {

    private final AccountService accountService;

    public Portfolio dtoToPortfolio(final PortfolioDto portfolioDto) {
        Portfolio portfolio = new Portfolio();
        portfolio.setAccount(accountService.findAccountById(portfolioDto.getAccountId()));
        portfolio.setName(portfolioDto.getPortfolioName());
        portfolio.setTotalValue(BigDecimal.ZERO);
        return portfolio;
    }

    public PortfolioDto portfolioToDto(final Portfolio portfolio) {
        return PortfolioDto.builder()
                .portfolioName(portfolio.getName())
                .portfolioId(portfolio.getId())
                .accountId(portfolio.getAccount().getId())
                .totalValue(portfolio.getTotalValue())
                .createdAt(portfolio.getCreatedAt().toString())
                .build();
    }
}
