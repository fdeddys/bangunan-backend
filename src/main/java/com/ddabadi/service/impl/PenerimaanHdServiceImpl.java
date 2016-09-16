package com.ddabadi.service.impl;

import com.ddabadi.domain.*;
import com.ddabadi.domain.repository.PenerimaanHdRepository;
import com.ddabadi.dto.DataPembayaranSupplierDto;
import com.ddabadi.dto.PenerimaanHdDto;
import com.ddabadi.dto.ReportBuktiTerimaDto;
import com.ddabadi.enumera.JenisTransaksiHistoryStock;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.exception.SupplierTidakValidException;
import com.ddabadi.exception.TanggalTidakValidException;
import com.ddabadi.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.peer.ScrollbarPeer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */

@Service
@Transactional
public class PenerimaanHdServiceImpl implements PenerimaanHdService {

    @Autowired private PenerimaanHdRepository penerimaanHdRepository;
    @Autowired private PenerimaanDtService penerimaanDtService;
    @Autowired private SupplierService supplierService;
    @Autowired private BarangService barangService;
    @Autowired private StockService stockService;
    @Autowired private GudangService gudangService;
    @Autowired private HistoryStockService historyStockService;
    @Autowired private CaraBayarService caraBayarService;

    private Logger logger = Logger.getLogger(PenerimaanHdService.class);

    @Override
    @Transactional(readOnly = true)
    public PenerimaanHd getById(Long id) {
        return penerimaanHdRepository.findOne(id);
    }

    @Override
    @Transactional
    public PenerimaanHd approve(Long id, Long idGudang) {
        // set stock
        // set approve
        // set history
        PenerimaanHd  penerimaanHd = penerimaanHdRepository.findOne(id);
        Iterator<PenerimaanDt> penerimaanDts = penerimaanDtService.getAllByIdHd(id).iterator();
        Gudang gudang = gudangService.getById(idGudang);
        while(penerimaanDts.hasNext()){
            Long qty;

            double curHpp;
            PenerimaanDt penerimaanDt = penerimaanDts.next();
            Barang barang = barangService.getById(penerimaanDt.getBarang().getId());
            Stock stock = stockService.getByBarangGudang(barang, gudang);
            if(stock ==null){
                qty=0L;
                stock = new Stock();
                stock.setBarang(barang);
                stock.setGudang(gudang);
                stock.setQty(qty);
                stock.setLastUpdate(new Date());
                stock.setKeterangan("Inisialisasi Awal success");
                stock.setCurHpp(0D);
                stock = stockService.save(stock);

                HistoryStock historyStockNew = new HistoryStock();
                historyStockNew.setGudang(gudang);
                historyStockNew.setKeterangan("Inisialisasi awal ");
                historyStockNew.setAkhir(0L);
                historyStockNew.setBarang(barang);
                historyStockNew.setCurrentHpp(0D);
                historyStockNew.setKeluar(0L);
                historyStockNew.setMasuk(0L);
                historyStockNew.setHargaTransaksi(0D);
                historyStockNew.setNoBukti("Initial");
                historyStockNew.setTglTransaksi(new Date());
                historyStockService.save(historyStockNew);

                curHpp=0D;
            }else{
                qty = stock.getQty();
                curHpp = qty * stock.getCurHpp();
            }
            curHpp = (curHpp + (penerimaanDt.getJumlah() * penerimaanDt.getHarga()) )/ (qty + penerimaanDt.getJumlah());

            qty=qty + penerimaanDt.getJumlah();
            stock.setQty(qty);
            stock.setLastUpdate(new Date());
            stock.setCurHpp(curHpp);

            HistoryStock historyStock = new HistoryStock();
            historyStock.setGudang(gudang);
            historyStock.setKeterangan("Penerimaan dari [" + penerimaanHd.getSupplier().getNama().trim() + "] no faktur [" + penerimaanHd.getNoFaktur().trim() + "]");
            historyStock.setAkhir(qty);
            historyStock.setBarang(barang);
            historyStock.setCurrentHpp(curHpp);
            historyStock.setKeluar(0L);
            historyStock.setMasuk(penerimaanDt.getJumlah());
            historyStock.setNoBukti(penerimaanHd.getId().toString());
            historyStock.setTglTransaksi(penerimaanHd.getTglTerima());
            historyStock.setHargaTransaksi(penerimaanDt.getHarga());
            historyStock.setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock.PENERIMAAN);
            stockService.save(stock);
            historyStockService.save(historyStock);
        }

        Double total=0D;
        total = penerimaanDtService.total(id);
        penerimaanHd.setStatusTerima(StatusTerima.APPROVED);
        penerimaanHd.setTotal(total);

        return penerimaanHdRepository.saveAndFlush(penerimaanHd);

    }

    @Override
    public PenerimaanHd save(PenerimaanHdDto penerimaanHdDto) {

        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglTerima ;
        if(penerimaanHdDto.getIdSupplier()==null){
            throw  new SupplierTidakValidException("supplier tidak valid");
        }
        Supplier supplier = supplierService.getById(penerimaanHdDto.getIdSupplier());
        try {
            tglTerima= tgl.parse(penerimaanHdDto.getTglTerima());
        } catch (ParseException e) {
            tglTerima = new Date();
            e.printStackTrace();
        }
        PenerimaanHd penerimaanHd = new PenerimaanHd();
        penerimaanHd.setUserUpdate( penerimaanHdDto.getUserName());
        penerimaanHd.setTglTerima(tglTerima);
        penerimaanHd.setSupplier(supplier);
        penerimaanHd.setStatusTerima(StatusTerima.OUTSTANDING);
        penerimaanHd.setKeterangan(penerimaanHdDto.getKeterangan());
        penerimaanHd.setLastUpdate(new Date());
        penerimaanHd.setNoFaktur(penerimaanHdDto.getNoFaktur());
        return penerimaanHdRepository.saveAndFlush(penerimaanHd);
    }

    @Override
    public PenerimaanHd update(Long idUpdate, PenerimaanHdDto penerimaanHdDto) {

        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglTerima ;
        Supplier supplier = supplierService.getById(penerimaanHdDto.getIdSupplier());
        try {
            tglTerima= tgl.parse(penerimaanHdDto.getTglTerima());
        } catch (ParseException e) {
            tglTerima = new Date();
            e.printStackTrace();
        }
        PenerimaanHd penerimaanHdUpdate = penerimaanHdRepository.findOne(idUpdate);
        penerimaanHdUpdate.setLastUpdate(new Date());
        penerimaanHdUpdate.setKeterangan(penerimaanHdDto.getKeterangan());
        penerimaanHdUpdate.setNoFaktur(penerimaanHdDto.getNoFaktur());
        penerimaanHdUpdate.setSupplier(supplier);
        penerimaanHdUpdate.setTglTerima(tglTerima);
        penerimaanHdUpdate.setUserUpdate(penerimaanHdDto.getUserName());
        return penerimaanHdRepository.saveAndFlush(penerimaanHdUpdate);
    }

    @Override
    public PenerimaanHd pembayaran(DataPembayaranSupplierDto dataPembayaranSupplierDto) {
        PenerimaanHd penerimaanHd = penerimaanHdRepository.findOne(dataPembayaranSupplierDto.getIdHd());
        CaraBayar caraBayar = caraBayarService.getById(dataPembayaranSupplierDto.getIdCaraBayar());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date tglBayar = sdf.parse(dataPembayaranSupplierDto.getTglBayar());
            penerimaanHd.setStatusTerima(StatusTerima.PAID);
            penerimaanHd.setLastUpdate(new Date());
            penerimaanHd.setTglBayar(tglBayar);
            penerimaanHd.setKeteranganBayar(dataPembayaranSupplierDto.getKeterangan());
            penerimaanHd.setCaraBayar(caraBayar);
            return penerimaanHdRepository.saveAndFlush(penerimaanHd);
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new TanggalTidakValidException(dataPembayaranSupplierDto.getTglBayar());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Page<PenerimaanHd> getByNamaSuppTgl(String namaSupp, Date tgl1, Date tgl2, int hal, int jumlah) {

        Sort sort = new Sort(Sort.Direction.DESC,"tglTerima").and(new Sort(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, sort);
        if(namaSupp.equals("--")){
            logger.info("get by nama supp = % tanggal " + tgl1.toString() +  " sd  " + tgl2  );
            return penerimaanHdRepository.findBySupplierNamaLikeAndTglTerimaBetween("%", tgl1, tgl2, pageRequest);
        }else{
            logger.info("get by nama supp = " + namaSupp + " tanggal " + tgl1.toString() +  " sd  " + tgl2  );
            return penerimaanHdRepository.findBySupplierNamaLikeAndTglTerimaBetween("%" + namaSupp + "%", tgl1, tgl2, pageRequest);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PenerimaanHd> getByIdSuppTglApproved(Long idSupp, Date tgl1, Date tgl2, int hal, int jumlah) {
        Supplier supplier = supplierService.getById(idSupp);
        Sort sort = new Sort(Sort.Direction.DESC,"tglTerima").and(new Sort(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, sort);
        return penerimaanHdRepository.findBySupplierAndTglTerimaBetweenAndStatusTerima(supplier,tgl1,tgl2,StatusTerima.APPROVED, pageRequest);
    }

    @Override
    public List<ReportBuktiTerimaDto> reportBuktiTerima(Long idHd) {

        PenerimaanHd penerimaanHd = penerimaanHdRepository.findOne(idHd);


        Iterator<PenerimaanDt> penerimaanDts = penerimaanDtService.getAllByIdHd(idHd).iterator();
        List<ReportBuktiTerimaDto> reportBuktiTerimaList = new ArrayList<ReportBuktiTerimaDto>();

        while (penerimaanDts.hasNext()){
            PenerimaanDt penerimaanDt = penerimaanDts.next();
            Double total;
            total = penerimaanDt.getJumlah() *penerimaanDt.getHarga();
            ReportBuktiTerimaDto reportBuktiTerimaDto = new ReportBuktiTerimaDto();
            reportBuktiTerimaDto.setKeterangan(penerimaanHd.getKeterangan());
            reportBuktiTerimaDto.setHarga(penerimaanDt.getHarga());
            reportBuktiTerimaDto.setIdHd(idHd);
            reportBuktiTerimaDto.setJumlah(penerimaanDt.getJumlah());
            reportBuktiTerimaDto.setNamaBarang(penerimaanDt.getBarang().getNama());
            reportBuktiTerimaDto.setNoFaktur(penerimaanHd.getNoFaktur());
            reportBuktiTerimaDto.setSatuan(penerimaanDt.getBarang().getSatuan().getNama());
            reportBuktiTerimaDto.setSupplier(penerimaanHd.getSupplier().getNama());
            reportBuktiTerimaDto.setTglTerima(penerimaanHd.getTglTerima());
            reportBuktiTerimaDto.setTotal(total);
            reportBuktiTerimaDto.setStatus(penerimaanHd.getStatusTerima().toString());
            reportBuktiTerimaList.add(reportBuktiTerimaDto);
        }
        return reportBuktiTerimaList;
    }

    @Override
    public List<PenerimaanHd> getByTanggal(Date tgl1, Date tgl2) {
        return penerimaanHdRepository.findByTglTerimaBetweenOrderByTglTerimaAsc(tgl1,tgl2);
    }

    @Override
    public List<PenerimaanHd> getByTanggalLunas(Date tgl1, Date tgl2, StatusTerima statusTerima) {
        return penerimaanHdRepository.findByTglBayarBetweenAndStatusTerimaOrderByTglBayarAsc(tgl1, tgl2, statusTerima);
    }

    @Override
    public Iterator<PenerimaanHd> getByTanggalBeliStatusTerima(Date tgl1, Date tgl2, StatusTerima statusTerima) {
        return penerimaanHdRepository.findByTglTerimaBetweenAndStatusTerima(tgl1, tgl2, statusTerima).iterator();
    }

    @Override
    public List<DashboardTopPenjualan> getTopSuppByTanggalJual(Date tgl1, Date tgl2) {
        return penerimaanHdRepository.findByTopSupplier(tgl1,tgl2);
    }


}
