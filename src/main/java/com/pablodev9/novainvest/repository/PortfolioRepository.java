package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
