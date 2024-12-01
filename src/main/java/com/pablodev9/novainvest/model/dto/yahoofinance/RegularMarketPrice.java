package com.pablodev9.novainvest.model.dto.yahoofinance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularMarketPrice {
    private double raw;
    private String fmt;
}
