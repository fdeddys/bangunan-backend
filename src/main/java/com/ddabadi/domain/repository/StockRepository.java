package com.ddabadi.domain.repository;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deddy on 5/24/16.
 */
public interface StockRepository extends JpaRepository<Stock,Long> {

    Stock findByGudangAndBarang(Gudang gudang, Barang barang);

}
