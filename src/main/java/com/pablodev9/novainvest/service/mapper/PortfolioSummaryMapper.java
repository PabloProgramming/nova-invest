package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioSummaryMapper {

    @Autowired
    private InvestmentMapper investmentMapper;

    public List<PortfolioSummaryDto> toSummaryDtos(final List<Portfolio> portfolios) {
        List<PortfolioSummaryDto> portfolioSummaryDtos = new ArrayList<>();
        for (Portfolio portfolio : portfolios) {
            PortfolioSummaryDto portfolioSummaryDto = PortfolioSummaryDto.builder()
                    .id(portfolio.getId())
                    .portfolioName(portfolio.getName())
                    .totalValue(portfolio.getTotalValue())
                    .investmentSummaryDtos(investmentMapper.toSummaryDtos(portfolio.getInvestments()))
                    .build();
            portfolioSummaryDtos.add(portfolioSummaryDto);
        }
        return portfolioSummaryDtos;
    }
}

