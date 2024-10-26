package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioResponseDto;
import com.pablodev9.novainvest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioDetailMapper {

    public PortfolioResponseDto entityToDto(final Portfolio portfolio) {
        return PortfolioResponseDto.builder()
                .accountId(portfolio.getAccount().getId())
                .portfolioId(portfolio.getId())
                .portfolioName(portfolio.getName())
                .totalValue(portfolio.getTotalValue())
                .updateAt(portfolio.getUpdatedAt().toString())
                .build();
    }

}
