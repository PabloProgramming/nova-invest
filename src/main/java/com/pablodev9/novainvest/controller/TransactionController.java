package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit-or-withdrawal")
    private ResponseEntity<TransactionResponseDto> depositOrWithdrawal(@RequestBody final TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.depositOrWithdrawal(transactionDto));
    }
}
