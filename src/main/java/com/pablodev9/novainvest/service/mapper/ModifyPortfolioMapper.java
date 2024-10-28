package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.ModifyPortfolioNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ModifyPortfolioMapper {

    public ModifyPortfolioNameDto portfolioToDto(final Portfolio portfolio) {
        return ModifyPortfolioNameDto.builder()
                .portfolioId(portfolio.getId())
                .portfolioName(portfolio.getName())
                .updatedAt(portfolio.getUpdatedAt())
                .build();
    }
}
