package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    private ResponseEntity<AccountPortfolioDto> getAccountPortfolios(@PathVariable final Long userId) {
        return ResponseEntity.ok(accountService.getAccountPortfolios(userId));
    }

}
