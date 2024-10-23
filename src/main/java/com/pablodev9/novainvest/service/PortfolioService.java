package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.repository.PortfolioRepository;
import com.pablodev9.novainvest.service.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioMapper portfolioMapper;

    private final PortfolioRepository portfolioRepository;

    public PortfolioDto createPortfolio(final PortfolioDto portfolioDto) {
        final Portfolio portfolio = portfolioMapper.dtoToPortfolio(portfolioDto);
        final Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.portfolioToDto(savedPortfolio);
    }
}
