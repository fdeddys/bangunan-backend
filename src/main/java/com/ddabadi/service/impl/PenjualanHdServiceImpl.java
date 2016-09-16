package com.ddabadi.service.impl;

import com.ddabadi.domain.*;
import com.ddabadi.domain.repository.PenjualanHdRepository;
import com.ddabadi.dto.DataPembayaranCustomerDto;
import com.ddabadi.dto.PenjualanHdDto;
import com.ddabadi.dto.ReportNotaPenjualanDto;
import com.ddabadi.enumera.JenisTransaksiHistoryStock;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.exception.SaldoCustomerTidakCukupException;
import com.ddabadi.exception.StokTidakCukupException;
import com.ddabadi.exception.TanggalTidakValidException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */
@Service
@Transactional
public class PenjualanHdServiceImpl implements PenjualanHdService {

    @Autowired private PenjualanHdRepository penjualanHdRepository;
    @Autowired private CustomerService customerService;
    @Autowired private PenjualanDtService penjualanDtService;
    @Autowired private StockService stockService;
    @Autowired private BarangService barangService;
    @Autowired private GudangService gudangService;
    @Autowired private HistoryStockService historyStockService;
    @Autowired private HistoryCustomerService historyCustomerService;
    @Autowired private CaraBayarService caraBayarService;

    private Logger logger = Logger.getLogger(PenjualanHdService.class);

    @Override
    public PenjualanHd getById(Long id) {
        logger.info("get by id " + id.toString());
        return penjualanHdRepository.findOne(id);
    }

    @Override
    public PenjualanHd save(PenjualanHdDto penjualanHdDto) {

        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglJual ;

        try {
            tglJual= tgl.parse(penjualanHdDto.getTglJual());
        } catch (ParseException e) {
            tglJual = new Date();
            e.printStackTrace();
        }
        Customer customer= customerService.getById(penjualanHdDto.getCustomerId());
        PenjualanHd penjualanHd = new PenjualanHd();
        penjualanHd.setKeterangan(penjualanHdDto.getKeterangan());
        penjualanHd.setCustomer(customer);
        penjualanHd.setLastUpdate(new Date());
        penjualanHd.setStatusJual(StatusTerima.OUTSTANDING);
        penjualanHd.setTglJual(tglJual);
        penjualanHd.setUserUpdate(penjualanHdDto.getUserUpdate());
        penjualanHd.setIsTransBarang(penjualanHdDto.getIsTransBarang());
        return penjualanHdRepository.saveAndFlush(penjualanHd);
    }

    @Override
    public PenjualanHd update(Long idUpdate, PenjualanHdDto penjualanHdDto) {
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        Date tglJual ;

        try {
            tglJual= tgl.parse(penjualanHdDto.getTglJual());
        } catch (ParseException e) {
            tglJual = new Date();
            e.printStackTrace();
        }
        Customer customer= customerService.getById(penjualanHdDto.getCustomerId());
        PenjualanHd penjualanHd = penjualanHdRepository.findOne(penjualanHdDto.getId());

        penjualanHd.setKeterangan(penjualanHdDto.getKeterangan());
        penjualanHd.setCustomer(customer);
        penjualanHd.setLastUpdate(new Date());
        penjualanHd.setStatusJual(StatusTerima.OUTSTANDING);
        penjualanHd.setTglJual(tglJual);
        penjualanHd.setUserUpdate(penjualanHdDto.getUserUpdate());
        penjualanHd.setIsTransBarang(penjualanHdDto.getIsTransBarang());
        return penjualanHdRepository.saveAndFlush(penjualanHd);
    }

    @Override
    public PenjualanHd approve(Long id, Long idGudang) {
        // cek kecukupan sisa saldo
        // update stock barang
        // insert history barang
        // update sisa saldo - total belanja
        // set approve
        // insert history customer

        Boolean isTransaksiBarang=true;

        PenjualanHd penjualanHd = penjualanHdRepository.findOne(id);

        isTransaksiBarang = penjualanHd.getIsTransBarang();
        Gudang gudang = gudangService.getById(idGudang);
        Iterator<PenjualanDt> penjualanDtIterator;
        if(isTransaksiBarang==true){
            penjualanDtIterator = penjualanDtService.getAllByPenjualanHdBarang(id).iterator();
        }else{
            penjualanDtIterator = penjualanDtService.getAllByPenjualanHdJasa(id).iterator();
        }

        // 1. cek kecukupan sisa saldo
        Customer customer= penjualanHd.getCustomer();

        // total belanja transaksi ini
        Double total = total(id);
        // total belanja semua transaksi customer
        Double totalBelanja ;
        if(isTransaksiBarang==true){
            totalBelanja = penjualanDtService.totalBarangByCustomer(customer.getId());
        }else{
            totalBelanja = penjualanDtService.totalJasaByCustomer(customer.getId());
        }

        Double sisa;
        sisa =customer.getMaxLimit() - (totalBelanja + total );
        if(sisa >0){

            // 2. update stock barang
            while (penjualanDtIterator.hasNext()){

                PenjualanDt penjualanDt = penjualanDtIterator.next();
                Barang barang = barangService.getById(penjualanDt.getBarang().getId());

                if(barang.isJasa()){
                    //jasa tidak pot stok dan tidak insert history
                    //3 insert history barang
                    HistoryStock historyStock = new HistoryStock();
                    historyStock.setHargaTransaksi(penjualanDt.getHarga());
                    historyStock.setGudang(gudang);
                    historyStock.setKeterangan("Jasa ke [" + penjualanHd.getCustomer().getNama().trim() + "]");
                    historyStock.setTglTransaksi(penjualanHd.getTglJual());
                    historyStock.setNoBukti(penjualanHd.getId().toString());
                    historyStock.setAkhir(0L);
                    historyStock.setBarang(barang);
                    historyStock.setCurrentHpp(0D);
                    historyStock.setKeluar(penjualanDt.getJumlah());
                    historyStock.setMasuk(0L);
                    historyStock.setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock.TRANS_JASA);
                    historyStockService.save(historyStock);

                }else{
                    // non jasa
                    Stock stock = stockService.getByBarangGudang(barang,gudang);
                    if(stock==null){
                        throw  new StokTidakCukupException("Barang = " + barang.getNama() + " belum ada penerimaan" );
                    }
                    Long sisaStock = stock.getQty() - penjualanDt.getJumlah();
                    if(sisaStock<0){
                        // raise error
                        throw new StokTidakCukupException(" Sisa stock [" + barang.getNama()  + "] = " + stock.getQty() + " !!" );
                    }
                    stock.setQty(sisaStock);
                    stock.setLastUpdate(new Date());
                    stockService.save(stock);

                    //3 insert history barang
                    HistoryStock historyStock = new HistoryStock();
                    historyStock.setHargaTransaksi(penjualanDt.getHarga());
                    historyStock.setGudang(gudang);
                    historyStock.setKeterangan("Penjualan ke [" + penjualanHd.getCustomer().getNama().trim() + "]");
                    historyStock.setTglTransaksi(penjualanHd.getTglJual());
                    historyStock.setNoBukti(penjualanHd.getId().toString());
                    historyStock.setAkhir(sisaStock);
                    historyStock.setBarang(barang);
                    historyStock.setCurrentHpp(barang.getHpp());
                    historyStock.setKeluar(penjualanDt.getJumlah());
                    historyStock.setMasuk(0L);
                    historyStock.setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock.PENJUALAN);
                    historyStockService.save(historyStock);
                }

            }
            //4  update sisa saldo - total belanja
            customer.setSisa(sisa);

            //5  insert history customer
            HistoryCustomer historyCustomer = new HistoryCustomer();
            if(isTransaksiBarang==true){
                historyCustomer.setKeterangan("Penjualan");
            }else {
                historyCustomer.setKeterangan("Jasa");
            }
            historyCustomer.setAkhir(totalBelanja);
            historyCustomer.setCustomer(customer);
            historyCustomer.setKeluar(0D);
            historyCustomer.setMasuk(total);
            historyCustomer.setIdTransaksi(penjualanHd.getId().toString());
            historyCustomer.setTglTransaksi(penjualanHd.getTglJual());

            penjualanHd.setTotal(total);
            penjualanHd.setStatusJual(StatusTerima.APPROVED);
            penjualanHd.setLastUpdate(new Date());


            // update all
            customerService.save(customer);
            penjualanHdRepository.save(penjualanHd);
            historyCustomerService.save(historyCustomer);

        }else{
            throw new SaldoCustomerTidakCukupException(" Total Belanja [" + totalBelanja + "] melebihi sisa = " + sisa.toString() );
        }
        return penjualanHd;

    }

    @Override
    public PenjualanHd batalApprove(Long id, Long idGudang) {

        // update stock barang
        // insert history barang
        // update sisa saldo + total qty detil
        // set CANCEL
        // insert history customer

        PenjualanHd penjualanHd = penjualanHdRepository.findOne(id);
        Gudang gudang = gudangService.getById(idGudang);
        Iterator<PenjualanDt> penjualanDtIterator = penjualanDtService.getAllByPenjualanHdBarang(id).iterator();

        // 1. cek kecukupan sisa saldo
        Customer customer= penjualanHd.getCustomer();
        // total belanja transaksi ini
        Double total = total(id);
        // total belanja semua transaksi customer
        Double totalBelanja = penjualanDtService.totalBarangByCustomer(customer.getId());

        // 2. update stock barang
        while (penjualanDtIterator.hasNext()){

            PenjualanDt penjualanDt = penjualanDtIterator.next();
            Barang barang = barangService.getById(penjualanDt.getBarang().getId());

            if(barang.isJasa()){
                //jasa tidak ada stok dan tidak insert history

            }else{
                Stock stock = stockService.getByBarangGudang(barang,gudang);

                Long sisaStock = stock.getQty() + penjualanDt.getJumlah();
                stock.setQty(sisaStock);
                stock.setLastUpdate(new Date());
                stockService.save(stock);

                //3 insert history barang
                HistoryStock historyStock = new HistoryStock();
                historyStock.setHargaTransaksi(penjualanDt.getHarga());
                historyStock.setGudang(gudang);
                historyStock.setKeterangan("CANCEL Penjualan ke [" + penjualanHd.getCustomer().getNama().trim() + "]");
                historyStock.setTglTransaksi(penjualanHd.getTglJual());
                historyStock.setNoBukti(penjualanHd.getId().toString());
                historyStock.setAkhir(sisaStock);
                historyStock.setBarang(barang);
                historyStock.setCurrentHpp(barang.getHpp());
                historyStock.setKeluar(0L);
                historyStock.setMasuk(penjualanDt.getJumlah());
                historyStock.setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock.RETUR_PENJUALAN);
                historyStockService.save(historyStock);

            }


        }

        //5  insert history customer
        HistoryCustomer historyCustomer = new HistoryCustomer();
        historyCustomer.setKeterangan("CANCEL Penjualan ");
        historyCustomer.setAkhir(totalBelanja);
        historyCustomer.setCustomer(customer);
        historyCustomer.setKeluar(0D);
        historyCustomer.setMasuk(-1 * total);
        historyCustomer.setIdTransaksi(penjualanHd.getId().toString());
        historyCustomer.setTglTransaksi(penjualanHd.getTglJual());

        penjualanHd.setStatusJual(StatusTerima.CANCELED);
        penjualanHd.setLastUpdate(new Date());


        // update all
        customerService.save(customer);
        penjualanHdRepository.save(penjualanHd);
        historyCustomerService.save(historyCustomer);

        return penjualanHd;
    }

    @Override
    public Page<PenjualanHd> getByNamaCustomerTanggalIsTransBarang(String nama, Date tgl1, Date tgl2, int hal, int jumlah) {
        logger.info("nama = " + nama + " tgl 1 = " + tgl1.toString() + " tgl 2 " + tgl2.toString() );

        Sort sort = new Sort(Sort.Direction.DESC,"tglJual").and(new Sort(Sort.Direction.ASC, "id"));

        PageRequest pageRequest= new PageRequest(hal-1,jumlah, sort);
        return penjualanHdRepository.findByCustomerNamaLikeAndTglJualBetweenAndIsTransBarangTrue("%" + nama + "%", tgl1, tgl2, pageRequest);
    }

    @Override
    public Page<PenjualanHd> getByNamaCustomerTanggalIsTransJasa(String nama, Date tgl1, Date tgl2, int hal, int jumlah) {
        logger.info("nama = " + nama + " tgl 1 = " + tgl1.toString() + " tgl 2 " + tgl2.toString() );

        Sort sort = new Sort(Sort.Direction.DESC,"tglJual").and(new Sort(Sort.Direction.ASC, "id"));

        PageRequest pageRequest= new PageRequest(hal-1,jumlah, sort);
        return penjualanHdRepository.findByCustomerNamaLikeAndTglJualBetweenAndIsTransBarangFalse("%" + nama + "%", tgl1, tgl2, pageRequest);
    }

    @Override
    public Double total(Long idHd) {
        Double totalAkhir=0D;
        Iterator<PenjualanDt> penjualanDts = penjualanDtService.getAllByPenjualanHdBarang(idHd).iterator();
        while (penjualanDts.hasNext()){
            PenjualanDt penjualanDt = penjualanDts.next();
            totalAkhir = totalAkhir +(penjualanDt.getJumlah() * penjualanDt.getHarga() );
        }
        return totalAkhir;
    }

    @Override
    public List<ReportNotaPenjualanDto> reportNotaPenjualan(Long idHd) {
        PenjualanHd penjualanHd= penjualanHdRepository.findOne(idHd);


        Iterator<PenjualanDt> penjualanDts= penjualanDtService.getAllByPenjualanHdBarang(idHd).iterator();
        List<ReportNotaPenjualanDto> reportNotaPenjualanList = new ArrayList<ReportNotaPenjualanDto>();

        while (penjualanDts.hasNext()){
            PenjualanDt penjualanDt = penjualanDts.next();
            Double total;
            total = penjualanDt.getHarga() * penjualanDt.getJumlah();
            ReportNotaPenjualanDto reportBuktiTerimaDto = new ReportNotaPenjualanDto();
            reportBuktiTerimaDto.setKeterangan(penjualanHd.getKeterangan());
            reportBuktiTerimaDto.setHarga(penjualanDt.getHarga());
            reportBuktiTerimaDto.setIdHd(idHd);
            reportBuktiTerimaDto.setJumlah(penjualanDt.getJumlah());
            reportBuktiTerimaDto.setNamaBarang(penjualanDt.getBarang().getNama());
            reportBuktiTerimaDto.setSatuan(penjualanDt.getBarang().getSatuan().getNama());
            reportBuktiTerimaDto.setCustomer(penjualanHd.getCustomer().getNama());
            reportBuktiTerimaDto.setTglJual(penjualanHd.getTglJual());
            reportBuktiTerimaDto.setTotal(total);
            reportBuktiTerimaDto.setStatus(penjualanHd.getStatusJual().toString());
            reportBuktiTerimaDto.setUser(penjualanHd.getUserUpdate());
            reportNotaPenjualanList.add(reportBuktiTerimaDto);

        }
        return reportNotaPenjualanList;
    }

    @Override
    public Page<PenjualanHd> getByNamaCustTglApproved(String namaCust, Date tgl1, Date tgl2, int hal, int jumlah) {
        Sort sort = new Sort(Sort.Direction.DESC,"tglJual").and(new Sort(Sort.Direction.ASC, "id"));
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, sort);
        return penjualanHdRepository.findByCustomerNamaLikeAndTglJualBetweenAndStatusJual(namaCust, tgl1, tgl2, StatusTerima.APPROVED, pageRequest);
    }

    @Override
    public PenjualanHd pembayaran(DataPembayaranCustomerDto dataPembayaranCustomerDto) {

        PenjualanHd penjualanHd= penjualanHdRepository.findOne(dataPembayaranCustomerDto.getIdHd());
        CaraBayar caraBayar = caraBayarService.getById(dataPembayaranCustomerDto.getIdCaraBayar());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Customer customer= penjualanHd.getCustomer();

        // total belanja semua transaksi customer
        Double totalBelanja = total(penjualanHd.getId());


        try {
            Date tglBayar = sdf.parse(dataPembayaranCustomerDto.getTglBayar());
            penjualanHd.setStatusJual(StatusTerima.PAID);
            penjualanHd.setLastUpdate(new Date());
            penjualanHd.setTglBayar(tglBayar);
            penjualanHd.setKeteranganBayar(dataPembayaranCustomerDto.getKeterangan());
            penjualanHd.setCaraBayar(caraBayar);

            HistoryCustomer historyCustomer = new HistoryCustomer();
            historyCustomer.setKeterangan("Pembayaran Tanggal Bayar " + dataPembayaranCustomerDto.getTglBayar() + " - " +  penjualanHd.getCaraBayar().getNama() );
            historyCustomer.setAkhir(totalBelanja);
            historyCustomer.setCustomer(customer);
            historyCustomer.setKeluar(totalBelanja);
            historyCustomer.setMasuk(0D);
            historyCustomer.setIdTransaksi(penjualanHd.getId().toString());
            historyCustomer.setTglTransaksi(new Date());
            historyCustomerService.save(historyCustomer);

            return penjualanHdRepository.saveAndFlush(penjualanHd);
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new TanggalTidakValidException(dataPembayaranCustomerDto.getTglBayar());
        }
    }

    @Override
    public List<PenjualanHd> getByCustomerStatusJualTanggalJual(Long idCust, StatusTerima statusJual, Date tgl1, Date Tgl2) {


        if(idCust==-1){
            if(statusJual ==null){
                // customer = null
                // status = null
                return penjualanHdRepository.findByTglJualBetweenOrderByTglJualAsc(tgl1, Tgl2);
            }else{
                // customer = null
                // status jual TIDAK null
                return penjualanHdRepository.findByStatusJualAndTglJualBetweenOrderByTglJualAsc(statusJual, tgl1, Tgl2);
            }
        }else{

            Customer customer = customerService.getById(idCust);
            if(statusJual==null){
                // customer = TIDAK null
                // status null
                return  penjualanHdRepository.findByCustomerAndTglJualBetweenOrderByTglJualAsc(customer, tgl1, Tgl2);
            }else{
                // customer = TIDAK null
                // status jual TIDAK null
                return  penjualanHdRepository.findByCustomerAndStatusJualAndTglJualBetweenOrderByTglJualAsc(customer, statusJual, tgl1, Tgl2);
            }
        }

    }

    @Override
    public List<PenjualanHd> getByCustomerTanggalLunas(Long idCust, Date tgl1, Date tgl2) {
        if(idCust==-1){
            return penjualanHdRepository.findByTglBayarBetweenOrderByTglBayarAsc(tgl1,tgl2);
        }else{
            Customer customer = customerService.getById(idCust);
            return  penjualanHdRepository.findByCustomerAndTglBayarBetweenOrderByTglBayarAsc(customer, tgl1, tgl2);
        }
    }

    @Override
    public Iterator<PenjualanHd> getByTanggalJualStatusJual(Date tgl1, Date tgl2, StatusTerima statusJual) {

        return penjualanHdRepository.findByTglJualBetweenAndStatusJual(tgl1, tgl2,statusJual).iterator();
    }

    @Override
    public List<DashboardTopPenjualan> getTopCustByTanggalJual(Date tgl1, Date tgl2) {
        return penjualanHdRepository.findByTopCustomer(tgl1,tgl2);
    }


    // END
}
