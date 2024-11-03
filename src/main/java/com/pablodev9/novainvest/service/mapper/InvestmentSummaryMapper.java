package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentSummaryDto;
import com.pablodev9.novainvest.service.InvestmentFinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvestmentSummaryMapper {

    private final InvestmentFinancialOperationService investmentFinancialOperationService;

    public List<InvestmentSummaryDto> toSummaryDtos(List<Investment> investments) {
        return investments.stream()
                .map(investment -> InvestmentSummaryDto.builder()
                        .investmentId(investment.getId())
                        .assetName(investment.getAsset().getName())
                        .amountInvested(investmentFinancialOperationService.calculateAmountInvested(investment))
                        .currentPrice(investment.getAsset().getCurrentPrice())
                        .profitOrLoss(investmentFinancialOperationService.calculateProfitOrLoss(investment))
                        .build())
                .collect(Collectors.toList());
    }
}