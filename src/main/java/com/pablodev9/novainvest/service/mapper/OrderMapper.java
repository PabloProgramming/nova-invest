package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Order;
import com.pablodev9.novainvest.model.dto.OrderDto;
import com.pablodev9.novainvest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderMapper {

    private final OrderService orderService;

    public List<OrderDto> toSummaryDtos(List<Order> orders) {
        return orders.stream()
                .map(order -> OrderDto.builder()
                        .orderId(order.getId())
                        .assetName(order.getAsset().getName())
                        .amountInvested(orderService.calculateAmountInvested(order))
                        .orderStatus(order.getOrderStatus())
                        .orderType(order.getOrderType())
                        .profitOrLoss(orderService.calculateProfitOrLoss(order))
                        .build())
                .collect(Collectors.toList());
    }

}
