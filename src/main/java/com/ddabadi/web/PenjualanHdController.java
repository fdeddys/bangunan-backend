package com.ddabadi.web;

import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.dto.*;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.PenjualanHdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by deddy on 6/7/16.
 */

@RestController
@RequestMapping("api/penjualanHd")
public class PenjualanHdController {

    @Autowired private PenjualanHdService penjualanHdService;

    @RequestMapping(value = "{id}",
                    produces = "application/json",
                    method = RequestMethod.GET)
    PenjualanHd getById(@PathVariable("id")Long id){

        return penjualanHdService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    PenjualanHd getById(@RequestBody PenjualanHdDto penjualanHdDto){

        return penjualanHdService.save(penjualanHdDto);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    PenjualanHd edit(@PathVariable("id")Long id,
                     @RequestBody PenjualanHdDto penjualanHdDto){

        return penjualanHdService.update(id, penjualanHdDto);
    }

    @RequestMapping(value = "/namaCust/{namaCust}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}/isTransBarang/{isTransBarang}",
            method = RequestMethod.GET)
    Page<PenjualanHd> getPenjualanByTglPage(@PathVariable("namaCust")String namaCust,
                                              @PathVariable("tgl1")String tgl1,
                                              @PathVariable("tgl2")String tgl2,
                                              @PathVariable("hal")int hal,
                                              @PathVariable("jumlah")int jumlah,
                                            @PathVariable("isTransBarang")Boolean isTransBarang){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        if(namaCust.equals("--")){
            namaCust="%";
        }
        try {
            Date tglAwal= sdf1.parse(tgl1);
            Date tglAkhir = sdf2.parse(tgl2);
            if(isTransBarang.equals(true)){
                return penjualanHdService.getByNamaCustomerTanggalIsTransBarang(namaCust, tglAwal, tglAkhir, hal, jumlah);
            }else{
                return  penjualanHdService.getByNamaCustomerTanggalIsTransJasa(namaCust, tglAwal, tglAkhir, hal, jumlah);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "new",method = RequestMethod.GET)
    public PenjualanHd getNew() {
        return new PenjualanHd();
    }

    @RequestMapping(value = "total/{id}",
            method = RequestMethod.GET)
    Double totalBelanja(@PathVariable("id")Long id){
        return penjualanHdService.total(id);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "approve/{idHd}/idGudang/{idGudang}")
    PenjualanHd approve(@PathVariable("idHd")Long idHd,
                        @PathVariable("idGudang")Long idGudang) {

        return penjualanHdService.approve(idHd, idGudang);
    }

    @RequestMapping(value = "/report/{id}",method = RequestMethod.GET)
    public void preview(@PathVariable("id")Long id,HttpServletResponse response)
    {
        List<ReportNotaPenjualanDto> reportNotaJual= penjualanHdService.reportNotaPenjualan(id);
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/transaksi/NotaPenjualan.jasper",maps,reportNotaJual,"Nota Penjualan",response);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "batalApprove/{idHd}/idGudang/{idGudang}")
    PenjualanHd batalApprove(@PathVariable("idHd")Long idHd,
                             @PathVariable("idGudang")Long idGudang) {

        return penjualanHdService.batalApprove(idHd, idGudang);
    }



    @RequestMapping(value = "approved/namaCust/{namaCust}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    ResponsePembayaranCustomerDto getPenjualanApproveByTglPage(@PathVariable("namaCust")String namaCust,
                                                                @PathVariable("tgl1")String tgl1,
                                                                @PathVariable("tgl2")String tgl2,
                                                                @PathVariable("hal")int hal,
                                                                @PathVariable("jumlah")int jumlah){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tglAwal= sdf1.parse(tgl1);
            Date tglAkhir = sdf2.parse(tgl2);
            Page<PenjualanHd> penjualanHdPage;
            if(namaCust == "-"){
                penjualanHdPage= penjualanHdService.getByNamaCustTglApproved("%", tglAwal, tglAkhir, hal, jumlah);
            }else{
                penjualanHdPage = penjualanHdService.getByNamaCustTglApproved("%"+namaCust+"%", tglAwal, tglAkhir, hal, jumlah);
            }
            Iterator<PenjualanHd> penjualanHds = penjualanHdPage.iterator();
            List<PembayaranCustomerDto> pembayaranCustomerDtos = new ArrayList<PembayaranCustomerDto>();
            while (penjualanHds.hasNext()){
                PenjualanHd penjualanHd= penjualanHds.next();
                PembayaranCustomerDto pembayaranCustomerDto = new PembayaranCustomerDto();
                pembayaranCustomerDto.setTotal(penjualanHdService.total(penjualanHd.getId()));
                pembayaranCustomerDto.setId(penjualanHd.getId());
                pembayaranCustomerDto.setKeterangan(penjualanHd.getKeterangan());
                pembayaranCustomerDto.setNamaCustomer(penjualanHd.getCustomer().getNama());
                pembayaranCustomerDto.setTglJual(penjualanHd.getTglJual());
                pembayaranCustomerDtos.add(pembayaranCustomerDto);

            }
            ResponsePembayaranCustomerDto responsePembayaranCustomerDto = new ResponsePembayaranCustomerDto();
            responsePembayaranCustomerDto.setContent(pembayaranCustomerDtos);
            responsePembayaranCustomerDto.setTotalElements(penjualanHdPage.getTotalElements());
            return responsePembayaranCustomerDto;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.POST,
                    value = "pembayaran")
    PenjualanHd bayar(@RequestBody DataPembayaranCustomerDto dataPembayaranCustomerDto) {

        return penjualanHdService.pembayaran(dataPembayaranCustomerDto);
    }




}
