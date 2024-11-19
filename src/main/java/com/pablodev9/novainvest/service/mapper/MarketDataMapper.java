package com.pablodev9.novainvest.service.mapper;

import com.pablodev9.novainvest.model.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

@RequiredArgsConstructor
@Service
public class MarketDataMapper {
    public static StockDto toDto(final Stock stock) {
        final StockQuote quote = stock.getQuote();
        final StockStats stats = stock.getStats();
        return StockDto.builder()
                .symbol(stock.getSymbol())
                .price(quote.getPrice())
                .change(quote.getChange())
                .changeInPercent(quote.getChangeInPercent())
                .marketCap(stats.getMarketCap())
                .dayHigh(quote.getDayHigh())
                .dayLow(quote.getDayLow())
                .yearHigh(quote.getYearHigh())
                .yearLow(quote.getYearLow())
                .volume(quote.getVolume())
                .avgVolume(quote.getAvgVolume())
                .build();
    }
}