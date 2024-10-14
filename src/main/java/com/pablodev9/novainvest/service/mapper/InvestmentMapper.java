package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Investment;
import com.pablodev9.novainvest.model.dto.InvestmentSummaryDto;
import com.pablodev9.novainvest.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentMapper {

    @Autowired
    private InvestmentService investmentService;

    public List<InvestmentSummaryDto> toSummaryDtos(List<Investment> investments) {
        List<InvestmentSummaryDto> investmentSummaryDtos = new ArrayList<>();
        for (Investment investment : investments) {
            InvestmentSummaryDto investmentSummaryDto = InvestmentSummaryDto.builder()
                    .investmentId(investment.getId())
                    .assetName(investment.getAsset().getName())
                    .amountInvested(investmentService.calculateAmountInvested(investment))
                    .currentValue(investment.getCurrentPrice())
                    .profitOrLoss(investmentService.calculateProfitOrLoss(investment))
                    .build();
            investmentSummaryDtos.add(investmentSummaryDto);
        }
        return investmentSummaryDtos;
    }

}
