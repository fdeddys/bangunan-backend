package com.ddabadi.service.impl;

import com.ddabadi.domain.CaraBayar;
import com.ddabadi.domain.repository.CaraBayarRepository;
import com.ddabadi.service.CaraBayarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deddy on 6/12/16.
 */

@Service
@Transactional
public class CaraBayarServiceImpl implements CaraBayarService {

    @Autowired private CaraBayarRepository repository;
    private Logger logger= Logger.getLogger(CaraBayarService.class);

    @Override
    public CaraBayar getById(Long id) {
        logger.info("get by id " + id.toString());
        return repository.findOne(id);
    }

    @Override
    public CaraBayar save(CaraBayar caraBayar) {
        logger.info("save");
        return repository.saveAndFlush(caraBayar);
    }

    @Override
    public CaraBayar update(Long idUpdate, CaraBayar caraBayar) {
        logger.info("update " + idUpdate.toString());
        CaraBayar caraBayarUpd = repository.findOne(idUpdate);
        caraBayarUpd.setNama(caraBayar.getNama());
        return repository.saveAndFlush(caraBayarUpd);
    }

    @Override
    public List<CaraBayar> getALl() {
        logger.info("get all" );
        return repository.findAll();
    }

    @Override
    public Page<CaraBayar> getByNama(String nama, int hal, int jumlah) {
        logger.info("get by nama page " + nama);
        Sort sort= new Sort(Sort.Direction.ASC,"nama","id");
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, sort);
        if(nama.equals("--")){
            return repository.findAll(pageRequest);
        }else{
            return repository.findByNama(nama, pageRequest);
        }
    }

}
