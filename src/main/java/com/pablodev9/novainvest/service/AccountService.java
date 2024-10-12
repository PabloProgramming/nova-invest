package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.*;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.enums.OrderStatus;
import com.pablodev9.novainvest.repository.AccountRepository;
import com.pablodev9.novainvest.service.mapper.AccountMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Lazy
    private AccountMapper accountMapper;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private InvestmentService investmentService;


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

    public Account save(Account account) {
        return accountRepository.save(account);
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

    public BigDecimal calculateBalance(Account account) {
        BigDecimal totalPortfolioValue = BigDecimal.ZERO;

        for (Portfolio portfolio : account.getPortfolios()) {
            if (portfolio != null) {
                totalPortfolioValue = totalPortfolioValue.add(portfolioService.calculateTotalValue(portfolio));
            }
        }

        return totalPortfolioValue.add(account.getEquity()).add(account.getReservedFunds());
    }

    public BigDecimal calculateEquity(Account account) {
        return calculateBalance(account)
                .subtract(calculateMargin(account))
                .subtract(calculateReservedFunds(account));
    }

    public BigDecimal calculateMargin(Account account) {
        BigDecimal totalMargin = BigDecimal.ZERO;

        for (Portfolio portfolio : account.getPortfolios()) {
            if (portfolio != null) {
                for (Investment investment : portfolio.getInvestments()) {
                    totalMargin = totalMargin.add(investmentService.calculateAmountInvested(investment));
                }
            }
        }
        return totalMargin;
    }

    public BigDecimal calculateReservedFunds(Account account) {
        BigDecimal totalReservedFunds = BigDecimal.ZERO;

        for (Portfolio portfolio : account.getPortfolios()) {
            if (portfolio != null) {
                for (Order order : portfolio.getOrders()) {
                    if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
                        totalReservedFunds = totalReservedFunds.add(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()))
                        );
                    }
                }
            }
        }
        return totalReservedFunds;
    }
}
