package com.ddabadi.service;


import com.ddabadi.domain.Gudang;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */
public interface GudangService {

    Gudang getById(Long id);
    Gudang save(Gudang gudang);
    Gudang update(Long idUpdate, Gudang gudang);
    Page<Gudang> getByNama(String nama, int hal, int jumlah);
    List<Gudang> getAll();
}
