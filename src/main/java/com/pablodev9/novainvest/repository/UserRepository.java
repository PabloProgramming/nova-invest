package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
