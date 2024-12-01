package com.pablodev9.novainvest.service.yahoofinance;

import com.pablodev9.novainvest.exceptionsHandler.exceptions.notFoundExceptions.StockNotFoundException;
import com.pablodev9.novainvest.model.dto.yahoofinance.QuoteSummary;
import com.pablodev9.novainvest.model.dto.yahoofinance.YahooFinanceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class GetRealTimeDataCall {

    @Value("${yahoo.api.base-url}")
    private String baseUrl;

    @Value("${yahoo.api.stock-get-price}")
    private String getStockGetPriceUrl;

    private final CommonHeadersYahooFinance commonHeadersYahooFinance;
    private final org.springframework.web.client.RestTemplate restTemplate;

    public QuoteSummary getStockData(final String symbol, final String region) {

        String url = baseUrl + getStockGetPriceUrl + "?region=" + region + "&symbol=" + symbol;

        HttpHeaders headers = commonHeadersYahooFinance.createHeaders();
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<YahooFinanceResponseDto> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, YahooFinanceResponseDto.class);

        if (response.getBody() == null) {
            throw new StockNotFoundException(symbol);
        }
        return response.getBody().getQuoteSummary();
    }
}
