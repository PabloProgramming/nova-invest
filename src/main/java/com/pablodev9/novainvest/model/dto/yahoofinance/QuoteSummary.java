package com.pablodev9.novainvest.model.dto.yahoofinance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteSummary {
    private List<Result> result;
}
