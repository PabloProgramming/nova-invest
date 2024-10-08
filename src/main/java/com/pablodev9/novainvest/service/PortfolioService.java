package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.repository.PortfolioRepository;
import com.pablodev9.novainvest.service.mapper.PortfolioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Autowired
    private PortfolioRepository portfolioRepository;

    public PortfolioDto createPortfolio(final PortfolioDto portfolioDto) {
        final Portfolio portfolio = portfolioMapper.dtoToPortfolio(portfolioDto);
        final Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.portfolioToDto(savedPortfolio);
    }
}
