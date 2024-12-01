package com.pablodev9.novainvest.model.dto.yahoofinance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class YahooFinanceResponseDto {
    private QuoteSummary quoteSummary;
}
