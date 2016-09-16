package com.ddabadi.service.impl;

import com.ddabadi.domain.Supplier;
import com.ddabadi.domain.repository.SupplierRepository;
import com.ddabadi.service.SupplierService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by deddy on 5/23/16.
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired private SupplierRepository repository;
    private Logger logger= Logger.getLogger(SupplierService.class);

    @Override
    public Supplier save(Supplier supplier) {
        logger.info("Save");
        return repository.saveAndFlush(supplier);
    }

    @Override
    public Supplier update(Long id, Supplier supplier) {
        logger.info("Update = "+ id.toString());
        Supplier supplierUpd = repository.findOne(id);
        supplierUpd.setNama(supplier.getNama());
        supplierUpd.setKota(supplier.getKota());
        supplierUpd.setKontak(supplier.getKontak());
        supplierUpd.setAlamat(supplier.getAlamat());
        supplierUpd.setTelp(supplier.getTelp());
        return repository.saveAndFlush(supplierUpd);
    }

    @Override
    public Supplier getById(Long id) {
        logger.info("get by id " + id.toString() );
        return repository.findOne(id);
    }

    @Override
    public List<Supplier> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Supplier> getByNama(String nama, int hal, int jumlah) {
        logger.info("Find by nama Page");
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"id");
        return repository.findByNamaLike(nama, pageRequest);
    }

    @Override
    public Boolean isNamaAda(String nama) {
        List<Supplier> suppliers= repository.findByNama(nama);

        if (suppliers.size()>0){
            return  true;
        }else{
            return false;
        }
    }
}
