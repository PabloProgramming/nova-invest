package com.pablodev9.novainvest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal totalValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nova_id")
    private User user;
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Investment> investments;

    public BigDecimal calculateTotalValue() {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (Investment investment : investments) {
            if (investment != null) {
                BigDecimal amountInvested = (investment.getAmountInvested() != null ? investment.getAmountInvested() : BigDecimal.ZERO);
                BigDecimal investmentValue = investment.calculateProfitOrLoss();
                totalValue = totalValue.add(amountInvested).add(investmentValue);
            }
        }
        return totalValue;
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
