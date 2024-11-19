package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Order;
import com.pablodev9.novainvest.model.dto.OrderDto;
import com.pablodev9.novainvest.model.dto.OrderResponseDto;
import com.pablodev9.novainvest.service.OrderFinancialOperationService;
import com.pablodev9.novainvest.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMapper {

    private final OrderFinancialOperationService orderFinancialOperationService;

    private final PortfolioService portfolioService;

    public Order dtoToOrder(final OrderDto orderDto) {
        Order order = new Order();
        order.setPortfolio(portfolioService.findPortfolioById(orderDto.getPortfolioId()));
        order.setOrderType(orderDto.getOrderType());
        order.setQuantity(orderDto.getQuantity());
        order.setTakeProfit(orderDto.getTakeProfit());
        order.setStopLoss(orderDto.getStopLoss());
        return order;
    }

    public OrderResponseDto orderToDto(final Order order) {
        return OrderResponseDto.builder()
                .portfolioId(order.getPortfolio().getId())
                .orderId(order.getId())
                .orderType(order.getOrderType())
                .assetId(order.getAsset().getId())
                .assetName(order.getAsset().getName())
                .quantity(order.getQuantity())
                .takeProfit(order.getTakeProfit())
                .stopLoss(order.getStopLoss())
                .transactionFees(order.getTransactionFees())
                .purchasePrice(order.getPurchasePrice())
                .currentValue(orderFinancialOperationService.calculateCurrentValue(order))
                .profitOrLoss(orderFinancialOperationService.calculateProfitOrLoss(order))
                .createdAt(order.getCreatedAt())
                .amountInvested(orderFinancialOperationService.calculateAmountInvested(order))
                .build();
    }
}