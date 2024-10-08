package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    private ResponseEntity<TransactionResponseDto> depositOrWithdrawal(@RequestBody final TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.depositOrWithdrawal(transactionDto));
    }
}
