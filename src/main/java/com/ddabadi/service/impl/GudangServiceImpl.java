package com.ddabadi.service.impl;

import com.ddabadi.domain.Gudang;
import com.ddabadi.domain.repository.GudangRepository;
import com.ddabadi.service.GudangService;
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
public class GudangServiceImpl implements GudangService {

    @Autowired private GudangRepository repository;

    private Logger logger= Logger.getLogger(GudangService.class);

    @Override
    public Gudang getById(Long id) {
        logger.info("get by id = " + id.toString());
        return repository.findOne(id);
    }

    @Override
    public Gudang save(Gudang gudang) {
        logger.info("save");
        return repository.saveAndFlush(gudang);
    }

    @Override
    public Gudang update(Long idUpdate, Gudang gudang) {
        logger.info("Update " + idUpdate.toString());
        Gudang gudangUpdate = repository.findOne(idUpdate);
        gudangUpdate.setNama(gudang.getNama());
        return repository.saveAndFlush(gudangUpdate);
    }

    @Override
    public Page<Gudang> getByNama(String nama, int hal, int jumlah) {
        logger.info("get by nama page " +nama);
        PageRequest pageRequest = new PageRequest(hal-1,jumlah, Sort.Direction.ASC,"nama","id");
        return repository.findByNamaLike("%"+nama+"%",pageRequest);
    }

    @Override
    public List<Gudang> getAll() {
        logger.info("get all ");
        Sort sort= new Sort(Sort.Direction.ASC,"nama","id");
        return repository.findAll(sort);
    }

}
