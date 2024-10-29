package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.model.dto.InvestmentSummaryDto;
import com.pablodev9.novainvest.service.AssetService;
import com.pablodev9.novainvest.service.InvestmentService;
import com.pablodev9.novainvest.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvestmentMapper {

    private final InvestmentService investmentService;

    private final PortfolioService portfolioService;

    private final AssetService assetService;

    public List<InvestmentSummaryDto> toSummaryDtos(List<Investment> investments) {
        return investments.stream()
                .map(investment -> InvestmentSummaryDto.builder()
                        .investmentId(investment.getId())
                        .assetName(investment.getAsset().getName())
                        .amountInvested(investmentService.calculateAmountInvested(investment))
                        .currentPrice(investment.getCurrentPrice())
                        .profitOrLoss(investmentService.calculateProfitOrLoss(investment))
                        .build())
                .collect(Collectors.toList());
    }

    public Investment dtoToInvestment(final InvestmentDto investmentDto){
        Investment investment = new Investment();
        investment.setPortfolio(portfolioService.findPortfolioById(investmentDto.getPortfolioId()));
        investment.setQuantity(investmentDto.getQuantity());
        investment.setAsset(assetService.findAssetById(investmentDto.getAssetId()));
        investment.setCreatedAt(investmentDto.getCreatedAt());
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
                .build();
    }
}