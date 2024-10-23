package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountMapper {

    private final PortfolioSummaryMapper portfolioSummaryMapper;


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
