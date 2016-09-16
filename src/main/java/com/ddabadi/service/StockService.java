package com.ddabadi.service;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.Stock;
import org.springframework.data.domain.Page;

/**
 * Created by deddy on 5/26/16.
 */
public interface StockService {
    Stock getById(Long id);
    Stock save(Stock stock);
    Stock update(Long idUpdate, Stock stock);
    Stock getByBarangGudang(Barang barang, Gudang gudang);

}
