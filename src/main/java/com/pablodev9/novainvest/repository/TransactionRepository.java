package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
