package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.UserDto;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.model.dto.UserResponseDto;
import com.pablodev9.novainvest.repository.AccountRepository;
import com.pablodev9.novainvest.service.mapper.AccountMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;


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

    public AccountPortfolioDto getAccountPortfolios(final Long userId) {
        final Account account = accountRepository.getAccountByUserId(userId);
        return accountMapper.toResponseDto(account);
    }

}
