package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentSummaryDto;
import com.pablodev9.novainvest.service.FinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvestmentSummaryMapper {

    private final FinancialOperationService financialOperationService;

    public List<InvestmentSummaryDto> toSummaryDtos(List<Investment> investments) {
        return investments.stream()
                .map(investment -> InvestmentSummaryDto.builder()
                        .investmentId(investment.getId())
                        .assetName(investment.getAsset().getName())
                        .amountInvested(financialOperationService.calculateAmountInvested(investment))
                        .currentPrice(investment.getCurrentPrice())
                        .profitOrLoss(financialOperationService.calculateProfitOrLoss(investment))
                        .build())
                .collect(Collectors.toList());
    }
}