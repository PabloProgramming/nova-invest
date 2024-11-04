package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.Watchlist;
import com.pablodev9.novainvest.model.dto.WatchlistDto;
import com.pablodev9.novainvest.repository.WatchlistRepository;
import com.pablodev9.novainvest.service.mapper.WatchlistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WatchlistService {

    private final WatchlistMapper watchlistMapper;

    private final PortfolioService portfolioService;

    private final WatchlistRepository watchlistRepository;

    public WatchlistDto createWatchlist(final WatchlistDto watchlistDto) {

        Watchlist watchlist = new Watchlist();
        watchlist.setPortfolio(portfolioService.findPortfolioById(watchlistDto.getPortfolioId()));
        watchlist.setName(watchlistDto.getName());
        final Watchlist savedWatchlist = watchlistRepository.save(watchlist);
        return watchlistMapper.watchlistToDto(savedWatchlist);
    }
}
