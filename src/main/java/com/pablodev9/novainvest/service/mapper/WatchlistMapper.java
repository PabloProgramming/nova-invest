package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.Watchlist;
import com.pablodev9.novainvest.model.dto.WatchlistDto;
import com.pablodev9.novainvest.model.dto.WatchlistResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WatchlistMapper {

    private final AssetMapper assetMapper;

    public List<WatchlistResponseDto> toSummaryDtos(final List<Watchlist> watchlists) {
        return watchlists.stream()
                .map(watchlist -> WatchlistResponseDto.builder()
                        .watchlistId(watchlist.getId())
                        .name(watchlist.getName())
                        .assetResponseDtos(assetMapper.toSummaryDtos(watchlist.getAssets()))
                        .build())
                .collect(Collectors.toList());
    }

    public WatchlistDto watchlistToDto(final Watchlist watchlist) {
        return WatchlistDto.builder()
                .portfolioId(watchlist.getPortfolio().getId())
                .name(watchlist.getName())
                .build();

    }


}

