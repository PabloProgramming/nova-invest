package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
