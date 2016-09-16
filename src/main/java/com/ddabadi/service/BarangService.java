package com.ddabadi.service;

import com.ddabadi.domain.Barang;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */
public interface BarangService {

    Barang getById(Long id);
    Barang save(Barang barang);
    Barang update(Long idUpdate, Barang barang);
    Page<Barang> getByNamaIsBarang(String nama, int hal, int jumlah);
    Page<Barang> getByNamaIsJasa(String nama, int hal, int jumlah);
    List<Barang> getAll();
    Boolean isNamaAda(String nama);

}
