package com.ddabadi.service.impl;

import com.ddabadi.domain.*;
import com.ddabadi.domain.repository.ReturSupplierHdRepository;
import com.ddabadi.dto.ReturHdDto;
import com.ddabadi.enumera.JenisTransaksiHistoryStock;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/10/16.
 */

@Service
@Transactional
public class ReturSupplierHdServiceImpl implements ReturSupplierHdService {

    @Autowired private ReturSupplierHdRepository repository;
    @Autowired private ReturSupplierDtService returSupplierDtService;
    @Autowired private SupplierService supplierService;
    @Autowired private GudangService gudangService;
    @Autowired private BarangService barangService;
    @Autowired private StockService stockService;
    @Autowired private HistoryStockService historyStockService;


    private Logger logger=Logger.getLogger(ReturSupplierHdService.class);

    @Override
    @Transactional(readOnly = true)
    public ReturSupplierHd getById(Long id) {
        logger.info("get by id " + id.toString());
        return repository.findOne(id);
    }

    @Override
    public ReturSupplierHd save(ReturHdDto returHdDto) {

        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglRetur ;

        try {
            tglRetur= tgl.parse(returHdDto.getTglRetur());
        } catch (ParseException e) {
            tglRetur = new Date();
            e.printStackTrace();
        }

        Supplier supplier = supplierService.getById(returHdDto.getIdSupplier());

        ReturSupplierHd returSupplierHd = new ReturSupplierHd();
        returSupplierHd.setStatusTerima(StatusTerima.OUTSTANDING);
        returSupplierHd.setUserUpdate(returHdDto.getUserUpdate());
        returSupplierHd.setTglRetur(tglRetur);
        returSupplierHd.setSupplier(supplier);
        returSupplierHd.setKeterangan(returHdDto.getKeterangan());
        returSupplierHd.setLastUpdate(new Date());
        return repository.saveAndFlush(returSupplierHd);
    }


    @Override
    public ReturSupplierHd update(Long idUpdate, ReturHdDto returHdDto) {
        logger.info("Update id = " + idUpdate.toString());

        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglRetur ;

        try {
            tglRetur= tgl.parse(returHdDto.getTglRetur());
        } catch (ParseException e) {
            tglRetur = new Date();
            e.printStackTrace();
        }

        Supplier supplier = supplierService.getById(returHdDto.getIdSupplier());

        ReturSupplierHd returSupplierHdUpd = repository.findOne(idUpdate);

        returSupplierHdUpd.setStatusTerima(StatusTerima.OUTSTANDING);
        returSupplierHdUpd.setUserUpdate(returHdDto.getUserUpdate());
        returSupplierHdUpd.setTglRetur(tglRetur);
        returSupplierHdUpd.setSupplier(supplier);
        returSupplierHdUpd.setKeterangan(returHdDto.getKeterangan());
        returSupplierHdUpd.setLastUpdate(new Date());

        return repository.saveAndFlush(returSupplierHdUpd);
    }

    @Override
    public Page<ReturSupplierHd> getByNamaTanggal(String nama, Date tgl1, Date tgl2, int hal, int jumlah) {
        logger.info("get by nama page " + nama);
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"id");
        return repository.findBySupplierNamaLikeAndTglReturBetween("%"+nama+"%", tgl1, tgl2, pageRequest);
    }

    @Override
    public ReturSupplierHd approve(Long idReturHd, Long idGudang) {

        // update stock barang
        // insert history barang
        // update sisa saldo - total qty
        // set approve

        ReturSupplierHd returSupplierHd = repository.findOne(idReturHd);
        Gudang gudang = gudangService.getById(idGudang);
        Iterator<ReturSupplierDt> returSupplierDtIterator = returSupplierDtService.getByIdHdList(idReturHd).iterator();


        Supplier supplier= returSupplierHd.getSupplier();

        // 1. update stock barang
        while (returSupplierDtIterator.hasNext()){

            ReturSupplierDt returSupplierDt = returSupplierDtIterator.next();
            Barang barang = barangService.getById(returSupplierDt.getBarang().getId());

            Stock stock = stockService.getByBarangGudang(barang,gudang);

            //2 insert stock
            Long sisaStock = stock.getQty() - returSupplierDt.getJumlah();
            stock.setQty(sisaStock);
            stock.setLastUpdate(new Date());
            stockService.save(stock);

            //3 insert history barang
            HistoryStock historyStock = new HistoryStock();
            historyStock.setHargaTransaksi(returSupplierDt.getHarga());
            historyStock.setGudang(gudang);
            historyStock.setKeterangan("Retur ke Supplier [" + returSupplierHd.getSupplier().getNama().trim() + "]");
            historyStock.setTglTransaksi(returSupplierHd.getTglRetur());
            historyStock.setNoBukti(returSupplierHd.getId().toString());
            historyStock.setAkhir(sisaStock);
            historyStock.setBarang(barang);
            historyStock.setCurrentHpp(barang.getHpp());
            historyStock.setKeluar(returSupplierDt.getJumlah());
            historyStock.setMasuk(0L);
            historyStock.setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock.RETUR_SUPPLIER);
            historyStockService.save(historyStock);

        }

        //3 insert history barang
        returSupplierHd.setStatusTerima(StatusTerima.APPROVED);
        returSupplierHd.setLastUpdate(new Date());

        return repository.save(returSupplierHd);
    }

    @Override
    public List<ReturSupplierHd> getBySupplierTanggalRetur(Supplier supplier, Date tgl1, Date tgl2) {
        if(supplier==null){
            return repository.findByTglReturBetween(tgl1, tgl2);
        }else{
            return repository.findBySupplierAndTglReturBetween(supplier, tgl1, tgl2);
        }
    }


    //END
}
