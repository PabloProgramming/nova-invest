package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.config.RestTemplateConfig;
import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.StockNotFoundException;
import com.pablodev9.novainvest.model.dto.StockDto;
import com.pablodev9.novainvest.model.dto.YahooFinanceResponseDto;
import com.pablodev9.novainvest.service.mapper.MarketDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MarketDataService {

    private final RestTemplateConfig restTemplateConfig;
    private final org.springframework.web.client.RestTemplate restTemplate;
    private final MarketDataMapper marketDataMapper;

    private static final String BASE_URL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com";

    public StockDto getStockData(final String symbol) {


        String url = BASE_URL + "?region=US&symbol=" + symbol;

        HttpHeaders headers = restTemplateConfig.createHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<YahooFinanceResponseDto> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, YahooFinanceResponseDto.class);

        if (response.getBody() == null || response.getBody().getQuotes() == null) {
            throw new StockNotFoundException(symbol);
        }

        return marketDataMapper.toDto((Stock) response.getBody().getQuotes());
    }

    public List<StockDto> getStocksData(final List<String> symbols) {
        List<StockDto> stockDtos = new ArrayList<>();
        symbols.forEach(symbol -> stockDtos.add(getStockData(symbol)));
        return stockDtos;
    }
}
