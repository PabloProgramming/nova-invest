package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.model.dto.yahoofinance.QuoteSummary;
import com.pablodev9.novainvest.service.yahoofinance.GetRealTimeDataCall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class YahooFinanceService {

    private final GetRealTimeDataCall getRealTimeDataCall;

    public QuoteSummary getRealTimeData(final String symbol, final String region) {
        return getRealTimeDataCall.getStockData(symbol, region);
    }

}
