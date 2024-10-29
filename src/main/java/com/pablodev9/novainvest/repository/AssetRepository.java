package com.pablodev9.novainvest.repository;

import com.pablodev9.novainvest.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
