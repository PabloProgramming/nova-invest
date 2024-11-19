package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Order;
import com.pablodev9.novainvest.model.dto.OrderSummaryDto;
import com.pablodev9.novainvest.service.OrderFinancialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderSummaryMapper {

    private final OrderFinancialOperationService orderFinancialOperationService;

    public List<OrderSummaryDto> toSummaryDtos(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderSummaryDto.builder()
                        .orderId(order.getId())
                        .assetName(order.getAsset().getName())
                        .amountInvested(orderFinancialOperationService.calculateAmountInvested(order))
                        .orderStatus(order.getOrderStatus())
                        .orderType(order.getOrderType())
                        .profitOrLoss(orderFinancialOperationService.calculateProfitOrLoss(order))
                        .build())
                .collect(Collectors.toList());
    }

}
