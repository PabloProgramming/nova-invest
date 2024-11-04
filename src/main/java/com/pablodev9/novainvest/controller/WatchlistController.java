package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.WatchlistDto;
import com.pablodev9.novainvest.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/create")
    private ResponseEntity<WatchlistDto> createWatchlist(@RequestBody final WatchlistDto watchlistDto) {
        return ResponseEntity.ok(watchlistService.createWatchlist(watchlistDto));
    }
}
