package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/add")
    private ResponseEntity<InvestmentResponseDto> addInvestmentToPortfolio(@RequestBody final InvestmentDto investmentDto) {
        return ResponseEntity.ok(investmentService.addInvestmentToPortfolio(investmentDto));
    }

    /*@GetMapping("/{portfolioId}")
    private ResponseEntity<PortfolioResponseDto> getPortfolioDetails(@PathVariable final Long portfolioId) {
        return ResponseEntity.ok(portfolioService.getPortfolioDetails(portfolioId));
    }

    @PutMapping("/update-details")
    private ResponseEntity<ModifyPortfolioNameDto> updatePortfolioName(@RequestBody final ModifyPortfolioNameDto modifyPortfolioNameDto) {
        return ResponseEntity.ok(portfolioService.updatePortfolioName(modifyPortfolioNameDto));
    }

    @DeleteMapping("/{portfolioId}")
    private ResponseEntity<Long> deletePortfolio(@PathVariable final Long portfolioId) {
        return ResponseEntity.ok(portfolioService.deletePortfolio(portfolioId));
    }*/

}
