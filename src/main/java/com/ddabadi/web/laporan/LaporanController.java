package com.ddabadi.web.laporan;

import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.domain.Supplier;
import com.ddabadi.dto.laporan.LaporanPembelianDto;
import com.ddabadi.dto.laporan.LaporanPenjualanDto;
import com.ddabadi.dto.laporan.LaporanReturSupplierDto;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.exception.TanggalTidakValidException;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by deddy on 6/25/16.
 */

@RestController
@RequestMapping(value = "api/laporan")
public class LaporanController {

    @Autowired private PenerimaanHdService penerimaanHdService;
    @Autowired private PenerimaanDtService penerimaanDtService;
    @Autowired private ReturSupplierHdService returSupplierHdService;
    @Autowired private ReturSupplierDtService returSupplierDtService;
    @Autowired private SupplierService supplierService;

    @Autowired private PenjualanHdService penjualanHdService;

    @RequestMapping(value = "penerimaanPerPeriode/tgl1/{tgl1}/tgl2/{tgl2}/idSupp/{idSupp}/status/{statusBeli}",
                    method = RequestMethod.GET)
    public void laporanPenerimaan(@PathVariable("tgl1")String tgl1,
                                  @PathVariable("tgl2")String tgl2,
                                  @PathVariable("idSupp")Long idSupp,
                                  @PathVariable("statusBeli")String statusBeli,
                                  HttpServletResponse response){

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat periodeDate = new SimpleDateFormat("dd-MMMM-yyyy");

        Boolean tambah;

        try {
            Date tglAwal = formatDate.parse(tgl1);
            Date tglAkhir = formatDate.parse(tgl2);

            String periode1 = periodeDate.format(tglAwal);
            String periode2 = periodeDate.format(tglAkhir);

            List<LaporanPembelianDto> datasource= new ArrayList<LaporanPembelianDto>();

            Iterator<PenerimaanHd> penerimaanHds = penerimaanHdService.getByTanggal(tglAwal, tglAkhir).iterator();
            while (penerimaanHds.hasNext()){
                PenerimaanHd penerimaanHd = penerimaanHds.next();
                if(idSupp==-1){
                    tambah=true;
                }else{
                    if(penerimaanHd.getSupplier().getId()==idSupp){
                        tambah=true;


                    }else{
                        tambah=false;
                    }
                }

                if(statusBeli.equals("ALL")){

                }else{
                    if(penerimaanHd.getStatusTerima().toString().equals(statusBeli)){

                    }else{
                        tambah=false;
                    }
                }

                if(tambah){
                    LaporanPembelianDto laporanPembelianDto = new LaporanPembelianDto();
                    laporanPembelianDto.setStatusTerima(penerimaanHd.getStatusTerima());
                    laporanPembelianDto.setId(penerimaanHd.getId());
                    laporanPembelianDto.setTotal(penerimaanDtService.total(laporanPembelianDto.getId()));
                    laporanPembelianDto.setKeterangan(penerimaanHd.getKeterangan());
                    laporanPembelianDto.setNamaSupp(penerimaanHd.getSupplier().getNama());
                    laporanPembelianDto.setNoFaktur(penerimaanHd.getNoFaktur());
                    laporanPembelianDto.setTglTerima(penerimaanHd.getTglTerima());
                    if(penerimaanHd.getStatusTerima().toString().equals("PAID")){
                        laporanPembelianDto.setTglBayar(penerimaanHd.getTglBayar());
                        laporanPembelianDto.setCaraBayar(penerimaanHd.getCaraBayar().getNama());
                        laporanPembelianDto.setKetBayar(penerimaanHd.getKeteranganBayar());
                    }else{
                        laporanPembelianDto.setTglBayar(null);
                        laporanPembelianDto.setCaraBayar("");
                        laporanPembelianDto.setKetBayar(null);
                    }

                    datasource.add(laporanPembelianDto);
                }
            }

            Map<String,Object> maps = new HashMap<String, Object>();
            maps.put("periode","Tanggal " + periode1 + " sd " + periode2);
            ReportController report = new ReportController();

            report.previewReport("/report/laporan/laporanPembelianPerPeriode.jasper", maps, datasource, "Laporan", response);
        } catch (ParseException e) {
            throw  new TanggalTidakValidException("tgl error");
        }


    }



    @RequestMapping(value = "pembayaranPerPeriode/tgl1/{tgl1}/tgl2/{tgl2}/idSupp/{idSupp}",
                    method = RequestMethod.GET)
    public void laporanPembayaran(@PathVariable("tgl1")String tgl1,
                                  @PathVariable("tgl2")String tgl2,
                                  @PathVariable("idSupp")Long idSupp,
                                  HttpServletResponse response){

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat periodeDate = new SimpleDateFormat("dd-MMMM-yyyy");

        Boolean tambah;

        try {
            Date tglAwal = formatDate.parse(tgl1);
            Date tglAkhir = formatDate.parse(tgl2);

            String periode1 = periodeDate.format(tglAwal);
            String periode2 = periodeDate.format(tglAkhir);

            List<LaporanPembelianDto> datasource= new ArrayList<LaporanPembelianDto>();

            Iterator<PenerimaanHd> penerimaanHds = penerimaanHdService.getByTanggalLunas(tglAwal, tglAkhir, StatusTerima.PAID).iterator();
            while (penerimaanHds.hasNext()){
                PenerimaanHd penerimaanHd = penerimaanHds.next();
                if(idSupp==-1){
                    tambah=true;
                }else{
                    if(penerimaanHd.getSupplier().getId()==idSupp){
                        tambah=true;
                    }else{
                        tambah=false;
                    }
                }

                if(tambah){
                    LaporanPembelianDto laporanPembelianDto = new LaporanPembelianDto();
                    laporanPembelianDto.setStatusTerima(penerimaanHd.getStatusTerima());
                    laporanPembelianDto.setId(penerimaanHd.getId());
                    laporanPembelianDto.setTotal(penerimaanDtService.total(laporanPembelianDto.getId()));
                    laporanPembelianDto.setKeterangan(penerimaanHd.getKeterangan());
                    laporanPembelianDto.setNamaSupp(penerimaanHd.getSupplier().getNama());
                    laporanPembelianDto.setNoFaktur(penerimaanHd.getNoFaktur());
                    laporanPembelianDto.setTglTerima(penerimaanHd.getTglTerima());
                    if(penerimaanHd.getStatusTerima().toString().equals("PAID")){
                        laporanPembelianDto.setTglBayar(penerimaanHd.getTglBayar());
                        laporanPembelianDto.setCaraBayar(penerimaanHd.getCaraBayar().getNama());
                        laporanPembelianDto.setKetBayar(penerimaanHd.getKeteranganBayar());
                    }else{
                        laporanPembelianDto.setTglBayar(null);
                        laporanPembelianDto.setCaraBayar("");
                        laporanPembelianDto.setKetBayar(null);
                    }

                    datasource.add(laporanPembelianDto);
                }
            }

            Map<String,Object> maps = new HashMap<String, Object>();
            maps.put("periode","Tanggal " + periode1 + " sd " + periode2);
            ReportController report = new ReportController();

            report.previewReport("/report/laporan/laporanPelunasanPerPeriode.jasper", maps, datasource, "Laporan", response);
        } catch (ParseException e) {
            throw  new TanggalTidakValidException("tgl error");
        }


    }



    @RequestMapping(value = "returSupplierPerPeriode/tgl1/{tgl1}/tgl2/{tgl2}/idSupp/{idSupp}",
                    method = RequestMethod.GET)
    public void laporanReturSupplier(@PathVariable("tgl1")String tgl1,
                                     @PathVariable("tgl2")String tgl2,
                                     @PathVariable("idSupp")Long idSupp,
                                    HttpServletResponse response){

        Supplier supplier;
        if(idSupp==-1){
            supplier=null;
        }else{
            supplier = supplierService.getById(idSupp);
        }
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat periodeDate = new SimpleDateFormat("dd-MMMM-yyyy");

        try {
            Date tglAwal = formatDate.parse(tgl1);
            Date tglAkhir = formatDate.parse(tgl2);

            String periode1 = periodeDate.format(tglAwal);
            String periode2 = periodeDate.format(tglAkhir);

            List<LaporanReturSupplierDto> datasource= new ArrayList<LaporanReturSupplierDto>();

            Iterator<ReturSupplierHd> returSupplierHds= returSupplierHdService.getBySupplierTanggalRetur(supplier, tglAwal, tglAkhir).iterator();
            while (returSupplierHds.hasNext()){
                ReturSupplierHd returSupplierHd = returSupplierHds.next();

                LaporanReturSupplierDto laporanReturSupplierDto= new LaporanReturSupplierDto();
                laporanReturSupplierDto.setNamaSupp(returSupplierHd.getSupplier().getNama());
                laporanReturSupplierDto.setId(returSupplierHd.getId());
                laporanReturSupplierDto.setTotal(returSupplierDtService.total(laporanReturSupplierDto.getId()));
                laporanReturSupplierDto.setKeterangan(returSupplierHd.getKeterangan());
                laporanReturSupplierDto.setTglRetur(returSupplierHd.getTglRetur());
                laporanReturSupplierDto.setStatusRetur(returSupplierHd.getStatusTerima());

                datasource.add(laporanReturSupplierDto);

            }

            Map<String,Object> maps = new HashMap<String, Object>();
            maps.put("periode","Tanggal " + periode1 + " sd " + periode2);
            ReportController report = new ReportController();

            report.previewReport("/report/laporan/laporanReturSupplierPerPeriode.jasper", maps, datasource, "Laporan", response);
        } catch (ParseException e) {
            throw  new TanggalTidakValidException("tgl error");
        }

    }



    @RequestMapping(value = "penjualanPerPeriode/tgl1/{tgl1}/tgl2/{tgl2}/idCust/{idCust}/status/{statusJual}",
            method = RequestMethod.GET)
    public void laporanPenjualanPerPeriode(@PathVariable("tgl1")String tgl1,
                                  @PathVariable("tgl2")String tgl2,
                                  @PathVariable("idCust")Long idCust,
                                  @PathVariable("statusJual")String statusJual,
                                  HttpServletResponse response){


        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat periodeDate = new SimpleDateFormat("dd-MMMM-yyyy");

        StatusTerima statusJual1;

        try {
            Date tglAwal = formatDate.parse(tgl1);
            Date tglAkhir = formatDate.parse(tgl2);

            String periode1 = periodeDate.format(tglAwal);
            String periode2 = periodeDate.format(tglAkhir);

            List<LaporanPenjualanDto> datasource= new ArrayList<LaporanPenjualanDto>();

            if(statusJual.equals("ALL")){
                statusJual1=null;
            }else{
                statusJual1 = StatusTerima.valueOf(statusJual);
            }

            Iterator<PenjualanHd> penjualanHds= penjualanHdService.getByCustomerStatusJualTanggalJual(idCust, statusJual1, tglAwal, tglAkhir).iterator();
            while (penjualanHds.hasNext()){
                PenjualanHd penjualanHd= penjualanHds.next();

                LaporanPenjualanDto laporanPenjualanDto = new LaporanPenjualanDto();
                laporanPenjualanDto.setStatusJual(penjualanHd.getStatusJual());
                laporanPenjualanDto.setId(penjualanHd.getId());
                laporanPenjualanDto.setTotal(penerimaanDtService.total(penjualanHd.getId()));
                laporanPenjualanDto.setKeterangan(penjualanHd.getKeterangan());
                laporanPenjualanDto.setNamaCustomer(penjualanHd.getCustomer().getNama());
                laporanPenjualanDto.setTglJual(penjualanHd.getTglJual());
                if(penjualanHd.getStatusJual().toString().equals("PAID")){
                    laporanPenjualanDto.setTglBayar(penjualanHd.getTglBayar());
                    laporanPenjualanDto.setCaraBayar(penjualanHd.getCaraBayar().getNama());
                    laporanPenjualanDto.setKetBayar(penjualanHd.getKeteranganBayar());
                }else{
                    laporanPenjualanDto.setTglBayar(null);
                    laporanPenjualanDto.setCaraBayar("");
                    laporanPenjualanDto.setKetBayar(null);
                }

                datasource.add(laporanPenjualanDto);

            }

            Map<String,Object> maps = new HashMap<String, Object>();
            maps.put("periode","Tanggal " + periode1 + " sd " + periode2);
            ReportController report = new ReportController();

            report.previewReport("/report/laporan/penjualan/laporanPenjualanPerPeriode.jasper", maps, datasource, "Laporan", response);
        } catch (ParseException e) {
            throw  new TanggalTidakValidException("tgl error");
        }

    }


    @RequestMapping(value = "pelunasanCustomerPerPeriode/tgl1/{tgl1}/tgl2/{tgl2}/idCust/{idCust}",
                    method = RequestMethod.GET)
    public void laporanPembayaranCustomer(@PathVariable("tgl1")String tgl1,
                                          @PathVariable("tgl2")String tgl2,
                                          @PathVariable("idCust")Long idCust,
                                          HttpServletResponse response){

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat periodeDate = new SimpleDateFormat("dd-MMMM-yyyy");


        try {
            Date tglAwal = formatDate.parse(tgl1);
            Date tglAkhir = formatDate.parse(tgl2);

            String periode1 = periodeDate.format(tglAwal);
            String periode2 = periodeDate.format(tglAkhir);

            List<LaporanPenjualanDto> datasource= new ArrayList<LaporanPenjualanDto>();

            Iterator<PenjualanHd> penjualanHds = penjualanHdService.getByCustomerTanggalLunas(idCust,tglAwal, tglAkhir).iterator();
            while (penjualanHds.hasNext()){
                PenjualanHd penjualanHd= penjualanHds.next();

                LaporanPenjualanDto laporanPenjualanDto= new LaporanPenjualanDto();
                laporanPenjualanDto.setStatusJual(penjualanHd.getStatusJual());
                laporanPenjualanDto.setId(penjualanHd.getId());
                laporanPenjualanDto.setTotal(penerimaanDtService.total(penjualanHd.getId()));
                laporanPenjualanDto.setKeterangan(penjualanHd.getKeterangan());
                laporanPenjualanDto.setNamaCustomer(penjualanHd.getCustomer().getNama());
                laporanPenjualanDto.setTglJual(penjualanHd.getTglJual());
                laporanPenjualanDto.setTglBayar(penjualanHd.getTglBayar());
                laporanPenjualanDto.setCaraBayar(penjualanHd.getCaraBayar().getNama());
                laporanPenjualanDto.setKetBayar(penjualanHd.getKeteranganBayar());

                datasource.add(laporanPenjualanDto);

            }

            Map<String,Object> maps = new HashMap<String, Object>();
            maps.put("periode","Tanggal " + periode1 + " sd " + periode2);
            ReportController report = new ReportController();

            report.previewReport("/report/laporan/penjualan/laporanPelunasanCustomerPerPeriode.jasper", maps, datasource, "Laporan", response);
        } catch (ParseException e) {
            throw  new TanggalTidakValidException("tgl error");
        }


    }


    //END
}
