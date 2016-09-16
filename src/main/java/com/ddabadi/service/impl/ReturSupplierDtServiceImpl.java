package com.ddabadi.service.impl;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.ReturSupplierDt;
import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.domain.repository.ReturSupplierDtRepository;
import com.ddabadi.dto.ReturDtDto;
import com.ddabadi.service.BarangService;
import com.ddabadi.service.ReturSupplierDtService;
import com.ddabadi.service.ReturSupplierHdService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/11/16.
 */

@Service
@Transactional
public class ReturSupplierDtServiceImpl implements ReturSupplierDtService {

    @Autowired private ReturSupplierDtRepository repository;
    @Autowired private BarangService barangService;
    @Autowired private ReturSupplierHdService returSupplierHdService;

    private Logger logger = Logger.getLogger(ReturSupplierDtService.class);

    @Override
    @Transactional(readOnly = true)
    public ReturSupplierDt getById(Long id) {
        logger.info("get by id "+ id.toString());
        return repository.findOne(id);
    }

    @Override
    public ReturSupplierDt save(ReturDtDto returDtDto) {

        logger.info("save");

        Barang barang = barangService.getById(returDtDto.getIdBarang());
        ReturSupplierHd returSupplierHd = returSupplierHdService.getById(returDtDto.getIdHd());
        ReturSupplierDt returSupplierDt = new ReturSupplierDt();
        returSupplierDt.setBarang(barang);
        returSupplierDt.setHarga(returDtDto.getHarga());
        returSupplierDt.setJumlah(returDtDto.getJumlah());
        returSupplierDt.setReturSupplierHd(returSupplierHd);
        return repository.saveAndFlush(returSupplierDt);
    }

    @Override
    public ReturSupplierDt update(Long idUpdate, ReturDtDto returDtDto) {
        logger.info("update id " + idUpdate.toString());

        Barang barang = barangService.getById(returDtDto.getIdBarang());
        ReturSupplierHd returSupplierHd = returSupplierHdService.getById(returDtDto.getIdHd());
        ReturSupplierDt returSupplierDt = repository.findOne(idUpdate);

        returSupplierDt.setBarang(barang);
        returSupplierDt.setHarga(returDtDto.getHarga());
        returSupplierDt.setJumlah(returDtDto.getJumlah());
        returSupplierDt.setReturSupplierHd(returSupplierHd);
        return repository.saveAndFlush(returSupplierDt);
    }

    @Override
    public Page<ReturSupplierDt> getByIdHd(Long idHd, int hal, int jumlah) {
        ReturSupplierHd returSupplierHd = returSupplierHdService.getById(idHd);

        PageRequest pageRequest = new PageRequest(hal-1,jumlah, Sort.Direction.ASC,"id");
        return repository.findByReturSupplierHd(returSupplierHd, pageRequest);
    }

    @Override
    public List<ReturSupplierDt> getByIdHdList(Long idHd) {
        ReturSupplierHd returSupplierHd = returSupplierHdService.getById(idHd);

        return repository.findByReturSupplierHd(returSupplierHd);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Double total(Long id) {
        Double total=0D;
        ReturSupplierHd returSupplierHd = returSupplierHdService.getById(id);
        Iterator<ReturSupplierDt> returSupplierDts = repository.findByReturSupplierHd(returSupplierHd).iterator();
        while (returSupplierDts.hasNext()){
            ReturSupplierDt returSupplierDt = returSupplierDts.next();
            total = total + (returSupplierDt.getJumlah() * returSupplierDt.getHarga());
        }
        return total;
    }
}
