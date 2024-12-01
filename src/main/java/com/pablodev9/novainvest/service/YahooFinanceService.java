package com.pablodev9.novainvest.service;

import com.pablodev9.novainvest.service.yahoofinance.GetRealTimeDataCall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class YahooFinanceService {

    private final GetRealTimeDataCall getRealTimeDataCall;

    public double getAssetData(final String symbol, final String region) {
        return getRealTimeDataCall.getAssetPrice(symbol, region).getQuoteSummary().getResult().get(0).getPrice().getRegularMarketPrice().getRaw();
    }

}
