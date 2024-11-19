package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
