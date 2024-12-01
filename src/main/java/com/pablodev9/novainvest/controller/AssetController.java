package com.pablodev9.novainvest.controller;

import com.pablodev9.novainvest.model.dto.yahoofinance.QuoteSummary;
import com.pablodev9.novainvest.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/asset")
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/{region}/{symbol}")
    public ResponseEntity<QuoteSummary> getAssetBySymbol(@PathVariable final String symbol, @PathVariable final String region) {
        return ResponseEntity.ok(assetService.getAssetBySymbol(symbol, region));
    }

}
