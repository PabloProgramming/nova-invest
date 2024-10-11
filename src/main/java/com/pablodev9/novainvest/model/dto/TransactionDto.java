package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@RequiredArgsConstructor
public class TransactionDto {
    private final Long accountId;
    private final OperationType operationType;
    private final BigDecimal amount;
}
