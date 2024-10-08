package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.model.dto.PortfolioSummaryDto;
import com.pablodev9.novainvest.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioMapper {

    @Autowired
    private InvestmentMapper investmentMapper;

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<PortfolioSummaryDto> toSummaryDtos(final List<Portfolio> portfolios) {
        List<PortfolioSummaryDto> portfolioSummaryDtos = new ArrayList<>();
        for (Portfolio portfolio : portfolios) {
            PortfolioSummaryDto portfolioSummaryDto = PortfolioSummaryDto.builder()
                    .id(portfolio.getId())
                    .portfolioName(portfolio.getName())
                    .totalValue(portfolio.calculateTotalValue())
                    .investmentSummaryDtos(investmentMapper.toSummaryDtos(portfolio.getInvestments()))
                    .build();
            portfolioSummaryDtos.add(portfolioSummaryDto);
        }
        return portfolioSummaryDtos;
    }

    public Portfolio dtoToPortfolio(final PortfolioDto portfolioDto) {
        Portfolio portfolio = new Portfolio();
        portfolio.setAccount(portfolioRepository.getPortfolioByAccountId(portfolioDto.getAccountId()));
        portfolio.setName(portfolioDto.getPortfolioName());
        portfolio.setTotalValue(portfolio.getTotalValue());
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
