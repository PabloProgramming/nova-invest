package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PortfolioSummaryMapper {

    private final InvestmentMapper investmentMapper;

    public List<PortfolioSummaryDto> toSummaryDtos(final List<Portfolio> portfolios) {
        return portfolios.stream()
                .map(portfolio -> PortfolioSummaryDto.builder()
                        .id(portfolio.getId())
                        .portfolioName(portfolio.getName())
                        .totalValue(portfolio.getTotalValue())
                        .investmentSummaryDtos(investmentMapper.toSummaryDtos(portfolio.getInvestments()))
                        .build())
                .collect(Collectors.toList());
    }
}

