package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.Transaction;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.repository.TransactionRepository;
import com.pablodev9.novainvest.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionMapper transactionMapper;

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    @Transactional
    public TransactionResponseDto depositOrWithdrawal(final TransactionDto transactionDto) {
        final Transaction transaction = transactionMapper.requestDtoToEntity(transactionDto);
        accountService.updateAccountBalance(transactionDto);
        final Account updatedAccount = accountService.findAccountById(transactionDto.getAccountId());
        accountService.save(updatedAccount);
        final Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.entityToResponseDto(savedTransaction);
    }
}
