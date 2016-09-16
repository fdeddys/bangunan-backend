package com.ddabadi.service;

import com.ddabadi.domain.Satuan;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */
public interface SatuanService {
    Satuan getById(Long id);
    Satuan save(Satuan satuan);
    Satuan update(Long idUpdate, Satuan satuan);
    List<Satuan> getALl();
    Page<Satuan> getByNama(String nama, int hal, int jumlah);
}
