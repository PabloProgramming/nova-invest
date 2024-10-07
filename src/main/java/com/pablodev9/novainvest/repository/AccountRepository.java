package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Account;
import com.pablodev9.novainvest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountByUserId (final Long userId);
}
