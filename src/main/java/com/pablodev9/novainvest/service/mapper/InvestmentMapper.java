package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentSummaryDto;
import com.pablodev9.novainvest.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvestmentMapper {

    private final InvestmentService investmentService;

    public List<InvestmentSummaryDto> toSummaryDtos(List<Investment> investments) {
        return investments.stream()
                .map(investment -> InvestmentSummaryDto.builder()
                        .investmentId(investment.getId())
                        .assetName(investment.getAsset().getName())
                        .amountInvested(investmentService.calculateAmountInvested(investment))
                        .currentValue(investment.getCurrentPrice())
                        .profitOrLoss(investmentService.calculateProfitOrLoss(investment))
                        .build())
                .collect(Collectors.toList());
    }

}
