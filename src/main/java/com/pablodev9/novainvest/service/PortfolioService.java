package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.PortfolioNotFoundException;
import com.pablodev9.novainvest.model.Portfolio;
import com.pablodev9.novainvest.model.dto.ModifyPortfolioNameDto;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.model.dto.PortfolioResponseDto;
import com.pablodev9.novainvest.repository.PortfolioRepository;
import com.pablodev9.novainvest.service.mapper.ModifyPortfolioMapper;
import com.pablodev9.novainvest.service.mapper.PortfolioDetailMapper;
import com.pablodev9.novainvest.service.mapper.PortfolioMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioMapper portfolioMapper;

    private final PortfolioRepository portfolioRepository;

    private final PortfolioDetailMapper portfolioDetailMapper;

    private final ModifyPortfolioMapper modifyPortfolioMapper;

    private final PortfolioFinancialOperationService portfolioFinancialOperationService;

    public PortfolioDto createPortfolio(final PortfolioDto portfolioDto) {
        final Portfolio portfolio = portfolioMapper.dtoToPortfolio(portfolioDto);
        final Portfolio savedPortfolio = portfolioRepository.save(portfolio);
        return portfolioMapper.portfolioToDto(savedPortfolio);
    }

    public PortfolioResponseDto getPortfolioDetails(final Long portfolioId) {
        final Portfolio portfolio = findPortfolioById(portfolioId);
        return portfolioDetailMapper.entityToDto(portfolio);
    }

    public ModifyPortfolioNameDto updatePortfolioName(final ModifyPortfolioNameDto modifyPortfolioNameDto) {
        Portfolio portfolio = findPortfolioById(modifyPortfolioNameDto.getPortfolioId());
        portfolio.setName(modifyPortfolioNameDto.getPortfolioName());
        final Portfolio updatedPortfolio = portfolioRepository.save(portfolio);
        return modifyPortfolioMapper.portfolioToDto(updatedPortfolio);
    }

    public Long deletePortfolio(final Long portfolioId) {
        final Portfolio portfolio = findPortfolioById(portfolioId);
        portfolioRepository.delete(portfolio);
        return portfolioId;
    }

    @Transactional
    public void updatePortfolioById(final Long portfolioId) {
        Portfolio portfolio = findPortfolioById(portfolioId);
        BigDecimal totalValue = portfolioFinancialOperationService.calculatePortfolioValue(portfolio);
        portfolio.setTotalValue(totalValue);
        portfolioRepository.save(portfolio);
    }

    @SneakyThrows
    public Portfolio findPortfolioById(final Long portfolioId) {
        final Optional<Portfolio> optionalPortfolio = portfolioRepository.findById(portfolioId);
        if (optionalPortfolio.isPresent()) {
            return optionalPortfolio.get();
        }
        throw new PortfolioNotFoundException(portfolioId);
    }
}
