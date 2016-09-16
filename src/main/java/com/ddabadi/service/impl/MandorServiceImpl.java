package com.ddabadi.service.impl;

import com.ddabadi.domain.Mandor;
import com.ddabadi.domain.repository.MandorRepository;
import com.ddabadi.service.MandorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by deddy on 8/30/16.
 */

@Service
public class MandorServiceImpl implements MandorService {

    @Autowired private MandorRepository repository;

    @Override
    public Mandor getById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Mandor save(Mandor mandor) {
        return repository.saveAndFlush(mandor);
    }

    @Override
    public Mandor update(Long idUpdate, Mandor mandor) {
        Mandor mandorUpdate = repository.findOne(idUpdate);
        mandorUpdate.setNama(mandor.getNama());
        mandorUpdate.setKeterangan(mandor.getKeterangan());
        return repository.saveAndFlush(mandorUpdate);
    }

    @Override
    public Page<Mandor> getByNama(String nama, int hal, int jumlah) {
        Sort sort= new Sort(Sort.Direction.ASC, "nama" );
        PageRequest pageRequest= new PageRequest(hal-1,jumlah,sort);
        return repository.findByNamaLike("%"+nama+"%",pageRequest);
    }

}
