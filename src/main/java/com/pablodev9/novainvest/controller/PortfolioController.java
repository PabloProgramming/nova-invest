package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.PortfolioDto;
import com.pablodev9.novainvest.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/portfolios/create")
    private ResponseEntity<PortfolioDto> createPortfolio(@RequestBody final PortfolioDto portfolioDto) {
        return ResponseEntity.ok(portfolioService.createPortfolio(portfolioDto));
    }

}
