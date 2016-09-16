package com.ddabadi.service.impl;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.HistoryStock;
import com.ddabadi.domain.repository.BarangRepository;
import com.ddabadi.domain.repository.GudangRepository;
import com.ddabadi.domain.repository.HistoryStockRepository;
import com.ddabadi.service.HistoryStockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by deddy on 5/25/16.
 */

@Service
@Transactional
public class HistoryStockServiceImpl implements HistoryStockService {

    @Autowired private HistoryStockRepository historyStockRepository;
    @Autowired private BarangRepository barangRepository;
    @Autowired private GudangRepository gudangRepository;
    private Logger logger=Logger.getLogger(HistoryStockService.class);

    @Transactional(readOnly = true)
    @Override
    public Page<HistoryStock> getByBarangGudangTanggal(Long idBarang, Long idGudang, Date tglAwal, Date tglAkhir, int hal, int jumlah) {
        logger.info("get barang history by gudang barang tanggal");
        PageRequest pageRequest= new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"id");
        Barang barang = barangRepository.findOne(idBarang);
        Gudang gudang = gudangRepository.findOne(idGudang);
        return   historyStockRepository.findByBarangAndGudangAndTglTransaksiBetween(barang,gudang,tglAwal,tglAkhir, pageRequest);

    }

    @Override
    public HistoryStock save(HistoryStock historyStock) {
        logger.info("save");
        return historyStockRepository.saveAndFlush(historyStock);
    }
}
