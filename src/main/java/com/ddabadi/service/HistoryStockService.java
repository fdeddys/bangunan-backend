package com.ddabadi.service;

import com.ddabadi.domain.HistoryStock;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by deddy on 5/25/16.
 */
public interface HistoryStockService {

    Page<HistoryStock> getByBarangGudangTanggal(Long idBarang, Long idGudang, Date tglAwal, Date tglAkhir, int hal, int jumlah);
    HistoryStock save(HistoryStock historyStock);

}
