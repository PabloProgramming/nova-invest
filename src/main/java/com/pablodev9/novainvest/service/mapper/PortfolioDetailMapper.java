package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioDetailMapper {

    private final WatchlistMapper watchlistMapper;

    private final InvestmentSummaryMapper investmentSummaryMapper;

    private final OrderMapper orderMapper;

    public PortfolioResponseDto entityToDto(final Portfolio portfolio) {
        return PortfolioResponseDto.builder()
                .accountId(portfolio.getAccount().getId())
                .portfolioId(portfolio.getId())
                .portfolioName(portfolio.getName())
                .totalValue(portfolio.getTotalValue())
                .updateAt(portfolio.getUpdatedAt().toString())
                .watchlistResponseDtos(watchlistMapper.toSummaryDtos(portfolio.getWatchlists()))
                .investmentSummaryDtos(investmentSummaryMapper.toSummaryDtos(portfolio.getInvestments()))
                .orderResponseDtos(orderMapper.toSummaryDtos(portfolio.getOrders()))
                .build();
    }

}
