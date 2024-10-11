package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.repository.AccountRepository;
import com.pablodev9.novainvest.service.mapper.AccountMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;


    public void createAccountForUser(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setEquity(BigDecimal.ZERO);
        account.setMargin(BigDecimal.ZERO);
        account.setReservedFunds(BigDecimal.ZERO);
        account.setCreatedAt(account.getCreatedAt());
        accountRepository.save(account);
    }

    @Transactional
    public AccountPortfolioDto getAccountPortfolios(final Long userId) {
        final Account account = accountRepository.getAccountByUserId(userId);
        return accountMapper.toResponseDto(account);
    }

    @SneakyThrows
    public Account findAccountById(final Long id) {
        final Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        throw new Exception();
    }

    public void updateAccountBalance(final TransactionDto transactionDto) {
        Account account = findAccountById(transactionDto.getAccountId());
        final BigDecimal amount = transactionDto.getAmount();

        switch (transactionDto.getOperationType()) {
            case WITHDRAWAL -> withdrawal(account, amount);
            case DEPOSIT -> deposit(account, amount);
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
    }

    private void withdrawal(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    private void deposit(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }
}
