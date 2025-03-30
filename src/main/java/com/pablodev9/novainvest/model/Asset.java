package com.pablodev9.novainvest.model;

import com.pablodev9.novainvest.model.enums.AssetType;
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
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String symbol;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetType assetType;
    private BigDecimal currentPrice;
    private Boolean marketOpen;
    private LocalDateTime marketCloseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

}
