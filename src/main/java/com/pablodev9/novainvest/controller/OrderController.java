package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.OrderDto;
import com.pablodev9.novainvest.model.dto.OrderResponseDto;
import com.pablodev9.novainvest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    private ResponseEntity<OrderResponseDto> addOrderToPortfolio(@RequestBody final OrderDto orderDto) {
        return ResponseEntity.ok(orderService.addOrderToPortfolio(orderDto));
    }
/*
    @GetMapping("/{investmentId}")
    private ResponseEntity<InvestmentResponseDto> getInvestmentDetails(@PathVariable final Long investmentId) {
        return ResponseEntity.ok(orderService.getInvestmentDetails(investmentId));
    }

    @PatchMapping("{investmentId}/closed/")
    private ResponseEntity<Long> closeInvestment(@PathVariable final Long investmentId) {
        return ResponseEntity.ok(investmentService.closeInvestment(investmentId));
    }*/
}
