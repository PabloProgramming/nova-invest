package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.service.AccountFinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountMapper {

    private final PortfolioSummaryMapper portfolioSummaryMapper;

    private final AccountFinancialOperationService accountFinancialOperationService;

    public AccountPortfolioDto toResponseDto(final Account account) {
        return AccountPortfolioDto.builder()
                .id(account.getId())
                .balance(accountFinancialOperationService.calculateBalance(account))
                .equity(accountFinancialOperationService.calculateEquity(account))
                .margin(accountFinancialOperationService.calculateMargin(account))
                .reservedFunds(accountFinancialOperationService.calculateReservedFunds(account))
                .updatedAt(account.getUpdatedAt().toString())
                .portfolioSummaryDtos(portfolioSummaryMapper.toSummaryDtos(account.getPortfolios()))
                .build();
    }

}
