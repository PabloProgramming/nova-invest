package com.pablodev9.novainvest.service.yahoofinance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonHeadersYahooFinance {

    @Value("${yahoo.api.key}")
    private String apiKey;

    @Value("${yahoo.api.host}")
    private String apiHost;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", apiKey);
        headers.set("x-rapidapi-host", apiHost);
        return headers;
    }
}
