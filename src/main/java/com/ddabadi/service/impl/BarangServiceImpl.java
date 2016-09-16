package com.ddabadi.service.impl;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.repository.BarangRepository;
import com.ddabadi.service.BarangService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired private BarangRepository repository;

    private Logger logger=Logger.getLogger(BarangService.class);

    @Override
    public Barang getById(Long id) {
        logger.info("get by id = " + id.toString());

        return repository.findOne(id);
    }

    @Override
    public Barang save(Barang barang) {
        logger.info("save  ");
        return repository.saveAndFlush(barang);
    }

    @Override
    public Barang update(Long idUpdate, Barang barang) {

        logger.info("update by id = " + idUpdate.toString());

        Barang barangUpdate = repository.findOne(idUpdate);
        barangUpdate.setNama(barang.getNama());
        barangUpdate.setSatuan(barang.getSatuan());
        barangUpdate.setStatus(barang.getStatus());
        barangUpdate.setJasa(barang.isJasa());
        return repository.saveAndFlush(barangUpdate);
    }

    @Override
    public Page<Barang> getByNamaIsBarang(String nama, int hal, int jumlah) {
        PageRequest pageRequest = new PageRequest(hal-1,jumlah, Sort.Direction.ASC,"nama","id");
        return repository.findByNamaLikeAndJasaIsFalse("%" + nama.trim() + "%", pageRequest);
    }

    @Override
    public Page<Barang> getByNamaIsJasa(String nama, int hal, int jumlah) {
        PageRequest pageRequest = new PageRequest(hal-1,jumlah, Sort.Direction.ASC,"nama","id");
        return repository.findByNamaLikeAndJasaIsTrue("%" + nama.trim() + "%", pageRequest);
    }

    @Override
    public List<Barang> getAll() {
        logger.info("get all ");

        Sort sort = new Sort(Sort.Direction.ASC,"nama");
        return repository.findAll(sort);
    }

    @Override
    public Boolean isNamaAda(String nama) {
        List<Barang> barangs = repository.findByNama(nama);

        if (barangs.size()>0){
            return  true;
        }else{
            return false;
        }
    }
}
