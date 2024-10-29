package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
