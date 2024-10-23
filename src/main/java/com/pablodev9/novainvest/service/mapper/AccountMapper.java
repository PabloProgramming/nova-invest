package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    @Autowired
    private PortfolioSummaryMapper portfolioSummaryMapper;


    public AccountPortfolioDto toResponseDto(final Account account) {
        return AccountPortfolioDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .equity(account.getEquity())
                .margin(account.getMargin())
                .reservedFunds(account.getReservedFunds())
                .updatedAt(account.getUpdatedAt().toString())
                .portfolioSummaryDtos(portfolioSummaryMapper.toSummaryDtos(account.getPortfolios()))
                .build();
    }

}
