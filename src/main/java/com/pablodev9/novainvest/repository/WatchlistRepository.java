package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
