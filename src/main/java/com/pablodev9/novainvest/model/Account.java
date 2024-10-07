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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    private BigDecimal equity;
    private BigDecimal margin;
    private BigDecimal reservedFunds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Portfolio> portfolios;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public BigDecimal calculateBalance() {
        BigDecimal totalPortfolioValue = BigDecimal.ZERO;

        for (Portfolio portfolio : portfolios) {
            if (portfolio != null) {
                totalPortfolioValue = totalPortfolioValue.add(portfolio.calculateTotalValue());
            }
        }

        // Total balance is the sum of portfolio values + equity + reserved funds
        return totalPortfolioValue.add(equity).add(reservedFunds);
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