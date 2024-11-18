package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.StockDto;
import com.pablodev9.novainvest.service.MarketDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/market")
public class MarketController {

    private final MarketDataService marketDataService;

    @GetMapping("/stock/{symbol}")
    private ResponseEntity<StockDto> getStockData(@PathVariable final String symbol) {
        return ResponseEntity.ok(marketDataService.getStockData(symbol));
    }

    @GetMapping("/stocks")
    private ResponseEntity<List<StockDto>> getStockData(@PathVariable final List<String> symbols) {
        return ResponseEntity.ok(marketDataService.getStocksData(symbols));
    }

}
