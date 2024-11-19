package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.InvestmentDto;
import com.pablodev9.novainvest.model.dto.InvestmentResponseDto;
import com.pablodev9.novainvest.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    @PostMapping("/add")
    private ResponseEntity<InvestmentResponseDto> addInvestmentToPortfolio(@RequestBody final InvestmentDto investmentDto) {
        return ResponseEntity.ok(investmentService.addInvestmentToPortfolio(investmentDto));
    }

    @GetMapping("/{investmentId}")
    private ResponseEntity<InvestmentResponseDto> getInvestmentDetails(@PathVariable final Long investmentId) {
        return ResponseEntity.ok(investmentService.getInvestmentDetails(investmentId));
    }

    @PatchMapping("{investmentId}/closed/")
    private ResponseEntity<Long> closeInvestment(@PathVariable final Long investmentId) {
        return ResponseEntity.ok(investmentService.closeInvestment(investmentId));
    }
}
