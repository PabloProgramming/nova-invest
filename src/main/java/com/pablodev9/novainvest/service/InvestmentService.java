package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.InvestmentNotFoundException;
import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.repository.InvestmentRepository;
import com.pablodev9.novainvest.service.mapper.InvestmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvestmentService {

    private final InvestmentMapper investmentMapper;

    private final InvestmentRepository investmentRepository;

    private final AssetService assetService;

    private final InvestmentFinancialOperationService investmentFinancialOperationService;

    private final AccountService accountService;

    private final PortfolioService portfolioService;

    public InvestmentResponseDto addInvestmentToPortfolio(final InvestmentDto investmentDto) {
        final Asset asset = assetService.findAssetById(investmentDto.getAssetId());
        final BigDecimal currentPrice = asset.getCurrentPrice();

        final Investment investment = investmentMapper.dtoToInvestment(investmentDto);
        investment.setAsset(asset);
        investment.setPurchasePrice(currentPrice);
        investment.setAmountInvested(investmentFinancialOperationService.calculateAmountInvested(investment));
        investment.setTransactionFees(investmentFinancialOperationService.calculateTransactionFees(currentPrice, investmentDto.getQuantity()));
        final Investment savedInvestment = investmentRepository.save(investment);

        accountService.updateAccountById(investment.getPortfolio().getAccount().getId());
        portfolioService.updatePortfolioById(investmentDto.getPortfolioId());

        return investmentMapper.investmentToDto(savedInvestment);
    }

    public InvestmentResponseDto getInvestmentDetails(final Long investmentId) {
        final Investment investment = findInvestmentById(investmentId);
        accountService.updateAccountById(investment.getPortfolio().getAccount().getId());
        portfolioService.updatePortfolioById(investment.getPortfolio().getId());
        return investmentMapper.investmentToDto(investment);
    }

    @SneakyThrows
    public Investment findInvestmentById(final Long investmentId) {
        final Optional<Investment> optionalInvestment = investmentRepository.findById(investmentId);
        if (optionalInvestment.isPresent()) {
            return optionalInvestment.get();
        }
        throw new InvestmentNotFoundException(investmentId);
    }
}
