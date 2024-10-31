package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.repository.InvestmentRepository;
import com.pablodev9.novainvest.service.mapper.InvestmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class InvestmentService {

    private final InvestmentMapper investmentMapper;

    private final InvestmentRepository investmentRepository;

    private final AssetService assetService;

    private final FinancialOperationService financialOperationService;

    public InvestmentResponseDto addInvestmentToPortfolio(final InvestmentDto investmentDto) {
        final Asset asset = assetService.findAssetById(investmentDto.getAssetId());
        final BigDecimal currentPrice = asset.getCurrentPrice();

        final Investment investment = investmentMapper.dtoToInvestment(investmentDto);
        investment.setPurchasePrice(currentPrice);
        investment.setAsset(asset);
        investment.setTransactionFees(investment.getTransactionFees());
        investment.setTransactionFees(financialOperationService.calculateTransactionFees(currentPrice, investmentDto.getQuantity()));
        final Investment savedInvestment = investmentRepository.save(investment);
        return investmentMapper.investmentToDto(savedInvestment);
    }
}
