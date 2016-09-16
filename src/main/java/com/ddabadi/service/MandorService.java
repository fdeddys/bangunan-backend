package com.ddabadi.service;

import com.ddabadi.domain.Mandor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 8/30/16.
 */
public interface MandorService {

    Mandor getById(Long id);
    Mandor save(Mandor mandor);
    Mandor update(Long idUpdate, Mandor mandor);
    Page<Mandor> getByNama(String nama, int hal, int jumlah);
}
