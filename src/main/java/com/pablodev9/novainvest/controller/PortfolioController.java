package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.ModifyPortfolioNameDto;
import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.model.dto.PortfolioResponseDto;
import com.pablodev9.novainvest.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/create")
    private ResponseEntity<PortfolioDto> createPortfolio(@RequestBody final PortfolioDto portfolioDto) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioDto));
    }

    @GetMapping("/{portfolioId}")
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
    }

}
