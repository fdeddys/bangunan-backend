package com.ddabadi.service.impl;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.Stock;
import com.ddabadi.domain.repository.StockRepository;
import com.ddabadi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by deddy on 5/26/16.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired private StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    public Stock getById(Long id) {
        return stockRepository.findOne(id);
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.saveAndFlush(stock);
    }

    @Override
    public Stock update(Long idUpdate, Stock stock) {
        Stock stockUpdate = stockRepository.findOne(idUpdate);
        stockUpdate.setBarang(stock.getBarang());
        stockUpdate.setGudang(stock.getGudang());
        stockUpdate.setLastUpdate(new Date());
        stockUpdate.setQty(stock.getQty());
        stockUpdate.setKeterangan(stock.getKeterangan());
        return stockRepository.saveAndFlush(stockUpdate);
    }

    @Override
    public Stock getByBarangGudang(Barang barang, Gudang gudang) {

        return stockRepository.findByGudangAndBarang(gudang,barang);
    }
}
