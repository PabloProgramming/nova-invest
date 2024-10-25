package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit-or-withdrawal")
    private ResponseEntity<TransactionResponseDto> depositOrWithdrawal(@RequestBody final TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.depositOrWithdrawal(transactionDto));
    }

    @GetMapping("/history/{accountId}")
    private ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
    }
}
