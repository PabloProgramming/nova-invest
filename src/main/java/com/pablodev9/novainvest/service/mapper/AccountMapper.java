package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    @Autowired
    private PortfolioMapper portfolioMapper;
    @Autowired
    private AccountService accountService;

    public AccountPortfolioDto toResponseDto(final Account account) {
        return AccountPortfolioDto.builder()
                .id(account.getId())
                .balance(accountService.calculateBalance(account))
                .equity(accountService.calculateEquity(account))
                .margin(accountService.calculateMargin(account))
                .reservedFunds(accountService.calculateReservedFunds(account))
                .updatedAt(account.getUpdatedAt().toString())
                .portfolioSummaryDtos(portfolioMapper.toSummaryDtos(account.getPortfolios()))
                .build();
    }

}
