package com.ddabadi.domain.repository;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.HistoryStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 5/25/16.
 */
public interface HistoryStockRepository extends JpaRepository<HistoryStock,Long> {


    Page<HistoryStock> findByBarangAndGudangAndTglTransaksiBetween(Barang barang,Gudang gudang,Date tgl1, Date tgl2, Pageable pageable);

}
