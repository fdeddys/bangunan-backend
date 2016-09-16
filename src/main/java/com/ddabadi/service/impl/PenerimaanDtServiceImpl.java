package com.ddabadi.service.impl;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.PenerimaanDt;
import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.repository.PenerimaanDtRepository;
import com.ddabadi.dto.PenerimaanDtDto;
import com.ddabadi.service.BarangService;
import com.ddabadi.service.PenerimaanDtService;
import com.ddabadi.service.PenerimaanHdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */

@Service
@Transactional
public class PenerimaanDtServiceImpl implements PenerimaanDtService {

    @Autowired private PenerimaanDtRepository penerimaanDtRepository;
    @Autowired private PenerimaanHdService penerimaanHdService;
    @Autowired private BarangService barangService;

    @Transactional(readOnly = true)
    @Override
    public PenerimaanDt getById(Long id) {
        return penerimaanDtRepository.findOne(id);
    }

    @Override
    public PenerimaanDt save(PenerimaanDtDto penerimaanDtDto) {

        PenerimaanHd penerimaanHd = penerimaanHdService.getById(penerimaanDtDto.getIdPenerimaanHd());
        Barang barang = barangService.getById(penerimaanDtDto.getIdBarang());

        PenerimaanDt penerimaanDt = new PenerimaanDt();
        penerimaanDt.setPenerimaanHd(penerimaanHd);
        penerimaanDt.setJumlah(penerimaanDtDto.getJumlah());
        penerimaanDt.setHarga(penerimaanDtDto.getHarga());
        penerimaanDt.setBarang(barang);
        return penerimaanDtRepository.saveAndFlush(penerimaanDt);
    }

    @Override
    public PenerimaanDt update(Long idUpdate, PenerimaanDt penerimaanDt) {
        PenerimaanDt penerimaanDtUpdate = penerimaanDtRepository.findOne(idUpdate);
        penerimaanDtUpdate.setBarang(penerimaanDt.getBarang());
        penerimaanDtUpdate.setHarga(penerimaanDt.getHarga());
        penerimaanDtUpdate.setJumlah(penerimaanDt.getJumlah());
        penerimaanDtUpdate.setPenerimaanHd(penerimaanDt.getPenerimaanHd());
        return penerimaanDtRepository.saveAndFlush(penerimaanDtUpdate);
    }

    @Override
    public Page<PenerimaanDt> getByIdHd(Long idHd, int hal, int jumlah) {
        PenerimaanHd penerimaanHd = penerimaanHdService.getById(idHd);
        PageRequest pageRequest = new PageRequest(hal-1,jumlah, Sort.Direction.ASC,"id");
        return penerimaanDtRepository.findByPenerimaanHd(penerimaanHd,pageRequest);
    }

    @Override
    public List<PenerimaanDt> getAllByIdHd(Long idHd) {
        PenerimaanHd penerimaanHd = penerimaanHdService.getById(idHd);
        return penerimaanDtRepository.findAllByPenerimaanHd(penerimaanHd);
    }

    @Override
    public void delete(Long id) {
        penerimaanDtRepository.delete(id);
    }

    @Override
    public Double total(Long id) {
        Double totalAkhir=0D;
        PenerimaanHd penerimaanHd = penerimaanHdService.getById(id);
        Iterator<PenerimaanDt> penerimaanDts = penerimaanDtRepository.findAllByPenerimaanHd(penerimaanHd).iterator();
        while (penerimaanDts.hasNext()){
            PenerimaanDt penerimaanDt = penerimaanDts.next();
            totalAkhir = totalAkhir +(penerimaanDt.getHarga() * penerimaanDt.getJumlah() );
        }
        return totalAkhir;
    }

}
