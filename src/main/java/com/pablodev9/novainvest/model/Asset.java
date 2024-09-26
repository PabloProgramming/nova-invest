package com.pablodev9.novainvest.model;

import com.pablodev9.novainvest.model.enums.AssetType;
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
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AssetType assetType;
    private BigDecimal currentPrice;
    private Boolean marketOpen;
    private LocalDateTime marketCloseTime;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Investment> investments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;
    @ManyToMany(mappedBy = "assets", fetch = FetchType.LAZY)
    private List<Watchlist> watchlists;
}
