package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Transaction;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.dto.TransactionResponseDto;
import com.pablodev9.novainvest.repository.TransactionRepository;
import com.pablodev9.novainvest.service.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public TransactionResponseDto depositOrWithdrawal(final TransactionDto transactionDto) {
        final Transaction transaction = transactionMapper.requestDtoToEntity(transactionDto);
        final Transaction savedTransaction = transactionRepository.save(transaction);
        accountService.updateAccountBalance(transactionDto);
        return transactionMapper.entityToResponseDto(savedTransaction);
    }
}
