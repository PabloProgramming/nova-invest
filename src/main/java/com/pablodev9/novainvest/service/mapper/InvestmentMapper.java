package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.service.AssetService;
import com.pablodev9.novainvest.service.PortfolioService;
import com.pablodev9.novainvest.service.FinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvestmentMapper {

    private final FinancialOperationService financialOperationService;

    private final PortfolioService portfolioService;

    public Investment dtoToInvestment(final InvestmentDto investmentDto) {
        Investment investment = new Investment();
        investment.setPortfolio(portfolioService.findPortfolioById(investmentDto.getPortfolioId()));
        investment.setQuantity(investmentDto.getQuantity());
        return investment;
    }

    public InvestmentResponseDto investmentToDto(final Investment investment) {
        return InvestmentResponseDto.builder()
                .portfolioId(investment.getPortfolio().getId())
                .investmentId(investment.getId())
                .assetId(investment.getAsset().getId())
                .assetName(investment.getAsset().getName())
                .quantity(investment.getQuantity())
                .transactionFees(investment.getTransactionFees())
                .purchasePrice(investment.getPurchasePrice())
                .createdAt(investment.getCreatedAt())
                .amountInvested(financialOperationService.calculateAmountInvested(investment))
                .build();
    }
}