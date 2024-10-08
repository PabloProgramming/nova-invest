package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Transaction;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    @Autowired
    private AccountService accountService;

    public Transaction requestDtoToEntity(final TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setAccount(accountService.findAccountById(transactionDto.getAccountId()));
        return transaction;

    }

    public TransactionResponseDto entityToResponseDto(final Transaction transaction) {
        return TransactionResponseDto.builder()
                .transactionId(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .updatedBalance(transaction.getAccount().getBalance())
                .transactionDate(transaction.getCreatedAt())
                .build();
    }
}