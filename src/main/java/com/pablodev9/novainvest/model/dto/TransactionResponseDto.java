package com.pablodev9.novainvest.model.dto;

import com.pablodev9.novainvest.model.enums.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
public class TransactionResponseDto {
    private final Long transactionId;
    private final OperationType operationType;
    private final BigDecimal amount;
    private final BigDecimal updatedBalance;
    private final LocalDateTime transactionDate;

}
