package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions.InsufficientBalanceException;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.badRequestExceptions.InvalidTransactionTypeException;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.AccountNotFoundException;
import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.User;
import com.pablodev9.novainvest.model.dto.AccountPortfolioDto;
import com.pablodev9.novainvest.model.dto.TransactionDto;
import com.pablodev9.novainvest.model.enums.OrderStatus;
import com.pablodev9.novainvest.repository.AccountRepository;
import com.pablodev9.novainvest.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final UtilitiesService utilitiesService;

    private final InvestmentService investmentService;


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

    public void deleteAccount(Long userId) {
        final Account account = accountRepository.getAccountByUserId(userId);
        accountRepository.delete(account);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public AccountPortfolioDto getAccountPortfolios(final Long userId) {
        final Account account = accountRepository.getAccountByUserId(userId);
        return accountMapper.toResponseDto(account);
    }

    @SneakyThrows
    public Account findAccountById(final Long accountId) {
        final Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        throw new AccountNotFoundException(accountId);
    }

    @SneakyThrows
    public void updateAccountBalance(final TransactionDto transactionDto) {
        Account account = findAccountById(transactionDto.getAccountId());
        final BigDecimal amount = transactionDto.getAmount();

        switch (transactionDto.getTransactionType()) {
            case WITHDRAWAL -> withdrawal(account, amount);
            case DEPOSIT -> deposit(account, amount);
            default -> throw new InvalidTransactionTypeException(transactionDto.getTransactionType().toString());
        }
    }

    @SneakyThrows
    private void withdrawal(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(account.getBalance());
        }
        account.setBalance(account.getBalance().subtract(amount));
    }

    private void deposit(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }

    public BigDecimal calculateBalance(Account account) {
        BigDecimal totalPortfolioValue = account.getPortfolios().stream()
                .filter(Objects::nonNull)
                .map(utilitiesService::calculateTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPortfolioValue.add(account.getEquity()).add(account.getReservedFunds());
    }

    public BigDecimal calculateEquity(Account account) {
        return calculateBalance(account)
                .subtract(calculateMargin(account))
                .subtract(calculateReservedFunds(account));
    }

    public BigDecimal calculateMargin(Account account) {
        return account.getPortfolios().stream()
                .filter(Objects::nonNull) // Filter out null portfolios
                .flatMap(portfolio -> portfolio.getInvestments().stream()) // Flatten the investments into a single stream
                .map(investmentService::calculateAmountInvested) // Map each investment to its invested amount
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all amounts invested, starting from BigDecimal.ZERO
    }

    public BigDecimal calculateReservedFunds(Account account) {
        return account.getPortfolios().stream()
                .filter(Objects::nonNull) // Filter out null portfolios
                .flatMap(portfolio -> portfolio.getOrders().stream()) // Flatten the orders into a single stream
                .filter(order -> order.getOrderStatus().equals(OrderStatus.PENDING)) // Filter only pending orders
                .map(order -> order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()))) // Calculate reserved funds for each order
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all reserved funds, starting from BigDecimal.ZERO
    }
}
