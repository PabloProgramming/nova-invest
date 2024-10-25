package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Transaction;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionMapper {

    private final AccountService accountService;

    public Transaction requestDtoToEntity(final TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDto.getTransactionType());
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

    public List<TransactionDto> toResponseDtos(final List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> TransactionDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .transactionType(transaction.getTransactionType())
                        .amount(transaction.getAmount())
                        .build())
                .collect(Collectors.toList());
    }
}