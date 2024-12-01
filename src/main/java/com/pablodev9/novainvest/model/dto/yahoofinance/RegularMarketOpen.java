package com.pablodev9.novainvest.model.dto.yahoofinance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularMarketOpen {
    private double raw;
    private String fmt;
}
