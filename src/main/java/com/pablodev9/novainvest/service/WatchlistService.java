package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.AssetNotFoundException;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.WatchlistNotFoundException;
import com.pablodev9.novainvest.model.Asset;
import com.pablodev9.novainvest.model.Watchlist;
import com.pablodev9.novainvest.model.dto.WatchlistRequestDto;
import com.pablodev9.novainvest.model.dto.WatchlistResponseDto;
import com.pablodev9.novainvest.repository.WatchlistRepository;
import com.pablodev9.novainvest.service.mapper.WatchlistMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WatchlistService {

    private final WatchlistMapper watchlistMapper;

    private final PortfolioService portfolioService;

    private final WatchlistRepository watchlistRepository;

    private final AssetService assetService;

    public WatchlistResponseDto createWatchlist(final WatchlistRequestDto watchlistRequestDto) {

        Watchlist watchlist = new Watchlist();
        watchlist.setPortfolio(portfolioService.findPortfolioById(watchlistRequestDto.getPortfolioId()));
        watchlist.setName(watchlistRequestDto.getName());
        watchlist.setAssets(assetService.findAssetsById(watchlistRequestDto.getAssetIds()));
        final Watchlist savedWatchlist = watchlistRepository.save(watchlist);
        return watchlistMapper.toResponseDto(savedWatchlist);
    }

    public WatchlistResponseDto addAssetsToWatchlist(final WatchlistRequestDto watchlistRequestDto) {
        Watchlist watchlist = new Watchlist();
        watchlist.setPortfolio(portfolioService.findPortfolioById(watchlistRequestDto.getPortfolioId()));
        watchlist.setAssets(assetService.findAssetsById(watchlistRequestDto.getAssetIds()));
        final Watchlist updatedWatchlist = watchlistRepository.save(watchlist);
        return watchlistMapper.toResponseDto(updatedWatchlist);
    }

    @Transactional
    @SneakyThrows
    public Long removeAssetFromWatchlist(final Long watchlistId, final Long assetId) {
        final Watchlist watchlist = findWatchlistById(watchlistId);
        final Asset asset = assetService.findAssetById(assetId);
        if (!watchlist.getAssets().contains(asset)) {
            throw new AssetNotFoundException(assetId);
        }
        return assetId;
    }

    public Long deleteWatchlist(final Long watchlistId) {
        final Watchlist watchlist = findWatchlistById(watchlistId);
        watchlistRepository.delete(watchlist);
        return watchlistId;
    }

    @SneakyThrows
    public Watchlist findWatchlistById(final Long watchlistId) {
        final Optional<Watchlist> optionalWatchlist = watchlistRepository.findById(watchlistId);
        if (optionalWatchlist.isPresent()) {
            return optionalWatchlist.get();
        }
        throw new WatchlistNotFoundException(watchlistId);
    }



}
