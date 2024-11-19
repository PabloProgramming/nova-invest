package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.model.Order;
import com.pablodev9.novainvest.model.dto.OrderDto;
import com.pablodev9.novainvest.model.dto.OrderResponseDto;
import com.pablodev9.novainvest.repository.OrderRepository;
import com.pablodev9.novainvest.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final AssetService assetService;

    private final OrderFinancialOperationService orderFinancialOperationService;

    private final AccountService accountService;

    private final PortfolioService portfolioService;

    public OrderResponseDto addOrderToPortfolio(final OrderDto orderDto) {
        final Asset asset = assetService.findAssetById(orderDto.getAssetId());
        final BigDecimal currentPrice = asset.getCurrentPrice();

        final Order order = orderMapper.dtoToOrder(orderDto);
        order.setAsset(asset);
        order.setPurchasePrice(currentPrice);
        order.setAmountInvested(orderFinancialOperationService.calculateAmountInvested(order));
        order.setTransactionFees(orderFinancialOperationService.calculateTransactionFees(currentPrice, orderDto.getQuantity()));
        final Order savedOrder = orderRepository.save(order);

        accountService.updateAccountById(order.getPortfolio().getAccount().getId());
        portfolioService.updatePortfolioById(orderDto.getPortfolioId());

        return orderMapper.orderToDto(savedOrder);
    }

    /*public InvestmentResponseDto getInvestmentDetails(final Long investmentId) {
        final Investment investment = findInvestmentById(investmentId);
        accountService.updateAccountById(investment.getPortfolio().getAccount().getId());
        portfolioService.updatePortfolioById(investment.getPortfolio().getId());
        return investmentMapper.investmentToDto(investment);
    }

    public Long closeInvestment(final Long investmentId) {
        final Investment investment = findInvestmentById(investmentId);
        accountService.updateAccountById(investment.getPortfolio().getAccount().getId());
        investment.setClosed(true);
        investment.setClosedAt(LocalDateTime.now());
        investmentRepository.save(investment);
        return investment.getId();
    }


    @SneakyThrows
    public Investment findInvestmentById(final Long investmentId) {
        final Optional<Investment> optionalInvestment = investmentRepository.findById(investmentId);
        if (optionalInvestment.isPresent()) {
            return optionalInvestment.get();
        }
        throw new InvestmentNotFoundException(investmentId);
    }*/
}
