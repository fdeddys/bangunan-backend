package com.ddabadi.service.impl;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.HistoryJasaCustomer;
import com.ddabadi.domain.Mandor;
import com.ddabadi.domain.PenjualanJasa;
import com.ddabadi.domain.repository.PenjualanJasaRepository;
import com.ddabadi.dto.PenjualanJasaDto;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.exception.CustomerTidakValidException;
import com.ddabadi.exception.MandorTidakValid;
import com.ddabadi.exception.SaldoCustomerTidakCukupException;
import com.ddabadi.exception.TanggalTidakValidException;
import com.ddabadi.service.CustomerService;
import com.ddabadi.service.HistoryJasaService;
import com.ddabadi.service.MandorService;
import com.ddabadi.service.PenjualanJasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deddy on 9/4/16.
 */

@Service
public class PenjualanJasaServiceImpl implements PenjualanJasaService {

    @Autowired private PenjualanJasaRepository repository;
    @Autowired private HistoryJasaService historyJasaService;
    @Autowired private CustomerService customerService;
    @Autowired private MandorService mandorService;

    @Override
    public PenjualanJasa getById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public PenjualanJasa save(PenjualanJasaDto penjualanJasaDto) throws CustomerTidakValidException {

        Date tgl ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tgl= sdf.parse(penjualanJasaDto.getTglTransaksi());
        }catch (Exception e){
            e.printStackTrace();

            throw new TanggalTidakValidException("Tanggal " + penjualanJasaDto.getTglTransaksi());
        }

        Customer customer = customerService.getById(penjualanJasaDto.getIdCust());
        if (customer==null){

            throw  new CustomerTidakValidException(" ID Customer tidak ditemukan !!");
        }

        Mandor mandor = mandorService.getById(penjualanJasaDto.getIdMandor());
        if(mandor==null){

            throw  new MandorTidakValid("ID Mandor tidak ditemukan !!");
        }


        PenjualanJasa penjualanJasa = new PenjualanJasa();
        penjualanJasa.setCustomer(customer);
        penjualanJasa.setStatusJual(StatusTerima.OUTSTANDING);
        penjualanJasa.setTglTransaksi(tgl);
        penjualanJasa.setKeterangan(penjualanJasaDto.getKeterangan());
        penjualanJasa.setJumlah(penjualanJasaDto.getJumlah());
        penjualanJasa.setLastUpdate(new Date());
        penjualanJasa.setMandor(mandor);
        penjualanJasa.setUserUpdate(penjualanJasaDto.getUserUpdate());
        return repository.save(penjualanJasa);
    }

    @Override
    public PenjualanJasa update(Long idUpdate, PenjualanJasa penjualanJasa) {
        PenjualanJasa penjualanJasaUpd = repository.findOne(idUpdate);
        penjualanJasaUpd.setKeterangan(penjualanJasa.getKeterangan());
        penjualanJasaUpd.setCustomer(penjualanJasa.getCustomer());
        penjualanJasaUpd.setJumlah(penjualanJasa.getJumlah());
        penjualanJasaUpd.setLastUpdate(new Date());
        penjualanJasaUpd.setMandor(penjualanJasa.getMandor());
        penjualanJasaUpd.setStatusJual(penjualanJasa.getStatusJual());
        penjualanJasaUpd.setTglTransaksi(penjualanJasa.getTglTransaksi());
        penjualanJasaUpd.setUserUpdate(penjualanJasa.getUserUpdate());
        return repository.saveAndFlush(penjualanJasaUpd);
    }

    @Override
    public PenjualanJasa approve(Long id, Long idGudang) {

        Double sisa;

        // panggil transaksi
        PenjualanJasa penjualanjasa= repository.findOne(id);
        // panggil cust
        Customer customer= penjualanjasa.getCustomer();
        //cek sisa
        sisa =customer.getSisaJasa() - (penjualanjasa.getJumlah() );

        if(sisa >0){

            // jika cukup pot sisa
            customer.setSisa(sisa);

            //  insert history customer
            HistoryJasaCustomer history= new HistoryJasaCustomer();
            history.setTglTransaksi(penjualanjasa.getTglTransaksi());
            history.setJumlah(penjualanjasa.getJumlah());
            history.setCustomer(customer);
            history.setIdTransaksi(penjualanjasa.getId());
            history.setKeterangan(penjualanjasa.getKeterangan());
            history.setSisa(sisa);

            penjualanjasa.setStatusJual(StatusTerima.APPROVED);
            penjualanjasa.setLastUpdate(new Date());

            historyJasaService.save(history);
            customerService.save(customer);
            repository.saveAndFlush(penjualanjasa);
        }else{
            throw new SaldoCustomerTidakCukupException(" Total Jasa [" + penjualanjasa.getJumlah() + "] melebihi sisa = " + sisa.toString() );
        }
        return null;
    }

    @Override
    public PenjualanJasa batalApprove(Long id) {

        Double sisa;

        // panggil transaksi
        PenjualanJasa penjualanjasa= repository.findOne(id);
        // panggil cust
        Customer customer= penjualanjasa.getCustomer();
        //set sisa
        sisa =customer.getSisaJasa() + (penjualanjasa.getJumlah() );

        customer.setSisa(sisa);

        //  insert history customer
        HistoryJasaCustomer history= new HistoryJasaCustomer();
        history.setTglTransaksi(penjualanjasa.getTglTransaksi());
        history.setJumlah(-1 * penjualanjasa.getJumlah());
        history.setCustomer(customer);
        history.setIdTransaksi(penjualanjasa.getId());
        history.setKeterangan("BATAL TRANSAKSI [" + penjualanjasa.getKeterangan() + "]");
        history.setSisa(sisa);

        penjualanjasa.setStatusJual(StatusTerima.CANCELED);
        penjualanjasa.setLastUpdate(new Date());

        historyJasaService.save(history);
        customerService.save(customer);
        return repository.saveAndFlush(penjualanjasa);


    }

    @Override
    public Page<PenjualanJasa> getByNamaCustTglTerima(String namaCust, Date tgl1, Date tgl2, Integer hal, Integer jumlah) {
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, sort);
        return repository.findByTglTransaksiBetweenAndCustomerNamaLike(tgl1,tgl2,namaCust,pageRequest);
    }
}
