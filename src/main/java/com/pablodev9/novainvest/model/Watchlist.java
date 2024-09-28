package com.pablodev9.novainvest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "watchlist_assets",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private List<Asset> assets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nova_id")
    private User user;
}
