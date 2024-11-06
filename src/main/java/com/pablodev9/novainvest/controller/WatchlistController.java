package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.WatchlistRequestDto;
import com.pablodev9.novainvest.model.dto.WatchlistResponseDto;
import com.pablodev9.novainvest.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/create")
    private ResponseEntity<WatchlistResponseDto> createWatchlist(@RequestBody final WatchlistRequestDto watchlistRequestDto) {
        return ResponseEntity.ok(watchlistService.createWatchlist(watchlistRequestDto));
    }

    @PostMapping("/add-asset")
    private ResponseEntity<WatchlistResponseDto> addAssetsToWatchlist(@RequestBody final WatchlistRequestDto watchlistRequestDto) {
        return ResponseEntity.ok(watchlistService.addAssetsToWatchlist(watchlistRequestDto));
    }

    @DeleteMapping("/{watchlistId}/remove-asset/{assetId}")
    private ResponseEntity<Long> removeAssetFromWatchlist(@PathVariable final Long watchlistId, @PathVariable final Long assetId) {
        return ResponseEntity.ok(watchlistService.removeAssetFromWatchlist(watchlistId, assetId));
    }
}
