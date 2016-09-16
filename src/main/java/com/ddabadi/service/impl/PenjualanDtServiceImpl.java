package com.ddabadi.service.impl;

import com.ddabadi.domain.*;
import com.ddabadi.domain.repository.PenjualanDtRepository;
import com.ddabadi.dto.PenjualanDtDto;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.service.BarangService;
import com.ddabadi.service.CustomerService;
import com.ddabadi.service.PenjualanDtService;
import com.ddabadi.service.PenjualanHdService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */

@Service
@Transactional
public class PenjualanDtServiceImpl implements PenjualanDtService {

    @Autowired private PenjualanDtRepository repository;
    @Autowired private PenjualanHdService penjualanHdService;
    @Autowired private BarangService barangService;
    @Autowired private CustomerService customerService;

    private Logger logger = Logger.getLogger(PenjualanDtService.class);

    @Override
    public PenjualanDt getById(Long id) {
        logger.info("get by id " + id.toString());
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete id "+id.toString());
        repository.delete(id);
    }

    @Override
    public PenjualanDt save(PenjualanDtDto penjualanDtDto) {
        logger.info("save");
        Barang barang = barangService.getById(penjualanDtDto.getIdBarang());
        PenjualanHd penjualanHd = penjualanHdService.getById(penjualanDtDto.getIdPenjualanHd());
        PenjualanDt penjualanDt = new PenjualanDt();
        penjualanDt.setBarang(barang);
        penjualanDt.setHarga(penjualanDtDto.getHarga());
        penjualanDt.setJumlah(penjualanDtDto.getJumlah());
        penjualanDt.setPenjualanHd(penjualanHd);
        return repository.saveAndFlush(penjualanDt);
    }

    @Override
    public Page<PenjualanDt> getByPenjualanHd(Long penjualanHdId, int hal, int jumlah) {
        PenjualanHd penjualanHd = penjualanHdService.getById(penjualanHdId);
        Sort sort=new Sort(Sort.Direction.ASC,"id");
        PageRequest pageRequest = new PageRequest(hal-1,jumlah,sort);

        return repository.findByPenjualanHd(penjualanHd, pageRequest);
    }

    @Override
    public List<PenjualanDt> getAllByPenjualanHdBarang(Long penjualanHdId) {
        PenjualanHd penjualanHd = penjualanHdService.getById(penjualanHdId);
        return repository.findAllByPenjualanHdAndPenjualanHdIsTransBarangTrue(penjualanHd);
    }

    @Override
    public List<PenjualanDt> getAllByPenjualanHdJasa(Long penjualanHdId) {
        PenjualanHd penjualanHd = penjualanHdService.getById(penjualanHdId);
        return repository.findAllByPenjualanHdAndPenjualanHdIsTransBarangFalse(penjualanHd);
    }

    @Override
    public Double totalBarangByCustomer(Long idCustomer) {
        Customer customer = customerService.getById(idCustomer);
        Double totalAkhir=0D;
        Iterator<PenjualanDt> penjualanDts = repository.findByPenjualanHdCustomerAndPenjualanHdIsTransBarangTrue(customer).iterator();
        while (penjualanDts.hasNext()){
            PenjualanDt penjualanDt = penjualanDts.next();
            if(penjualanDt.getPenjualanHd().getStatusJual().equals(StatusTerima.APPROVED)){
                totalAkhir = totalAkhir +(penjualanDt.getJumlah() * penjualanDt.getHarga() );
            }
        }
        return totalAkhir;
    }

    @Override
    public Double totalJasaByCustomer(Long idCustomer) {
        Customer customer = customerService.getById(idCustomer);
        Double totalAkhir=0D;
        Iterator<PenjualanDt> penjualanDts = repository.findByPenjualanHdCustomerAndPenjualanHdIsTransBarangFalse(customer).iterator();
        while (penjualanDts.hasNext()){
            PenjualanDt penjualanDt = penjualanDts.next();
            if(penjualanDt.getPenjualanHd().getStatusJual().equals(StatusTerima.APPROVED)){
                totalAkhir = totalAkhir +(penjualanDt.getJumlah() * penjualanDt.getHarga() );
            }
        }
        return totalAkhir;
    }

    @Override
    public List<DashboardTopPenjualan> getTopCustomer(Date tgl1, Date tgl2) {
        return null;
        //repository.(tgl1,tgl2);
    }


}
