package com.pablodev9.novainvest.model;

import com.pablodev9.novainvest.model.enums.InvestmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double quantity;
    private BigDecimal purchasePrice;
    private BigDecimal currentPrice;
    private BigDecimal transactionFees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public BigDecimal calculateAmountInvested() {
        return purchasePrice.multiply(BigDecimal.valueOf(quantity)).add(transactionFees);
    }

    public BigDecimal calculateProfitOrLoss() {
        BigDecimal initialInvestmentCost = purchasePrice.multiply(BigDecimal.valueOf(quantity)).add(transactionFees);
        BigDecimal currentValue = currentPrice.multiply(BigDecimal.valueOf(quantity));
        return currentValue.subtract(initialInvestmentCost);
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
