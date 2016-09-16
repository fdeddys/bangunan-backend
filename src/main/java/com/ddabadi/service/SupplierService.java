package com.ddabadi.service;

import com.ddabadi.domain.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/23/16.
 */
public interface SupplierService {

    Supplier save(Supplier supplier);
    Supplier update(Long id,Supplier supplier);
    Supplier getById(Long id);
    List<Supplier> getAll();
    Page<Supplier> getByNama(String nama, int hal, int jumlah);
    Boolean isNamaAda(String nama);

}
