package com.ddabadi.service.impl;

import com.ddabadi.domain.Satuan;
import com.ddabadi.domain.repository.SatuanRepository;
import com.ddabadi.service.SatuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deddy on 5/29/16.
 */
@Service
@Transactional
public class SatuanServiceImpl implements SatuanService {

    @Autowired private SatuanRepository repository;

    @Override
    public Satuan getById(Long id) {

        return repository.findOne(id);
    }

    @Override
    public Satuan save(Satuan satuan) {
        return repository.saveAndFlush(satuan);
    }

    @Override
    public Satuan update(Long idUpdate, Satuan satuan) {
        Satuan satuanUpd = repository.findOne(idUpdate);
        satuanUpd.setNama(satuan.getNama());
        return repository.saveAndFlush(satuanUpd);
    }

    @Override
    public List<Satuan> getALl() {
        Sort sort= new Sort(Sort.Direction.ASC,"nama");
        return repository.findAll(sort);
    }

    @Override
    public Page<Satuan> getByNama(String nama, int hal, int jumlah) {
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"id");
        return repository.findByNamaLike("%"+nama+"%",pageRequest);
    }
}
