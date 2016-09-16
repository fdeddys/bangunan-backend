package com.ddabadi.web;

import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.repository.PenerimaanHdRepository;
import com.ddabadi.dto.*;
import com.ddabadi.exception.SupplierTidakValidException;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.PenerimaanDtService;
import com.ddabadi.service.PenerimaanHdService;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by deddy on 5/30/16.
 */

@RestController
@RequestMapping(value = "api/penerimaanHd", produces = "application/json")
public class PenerimaanHdController {


    @Autowired private PenerimaanHdService penerimaanHdService;
    @Autowired private PenerimaanDtService penerimaanDtService;

    @RequestMapping(value = "/namaSupp/{namaSupp}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    Page<PenerimaanHd> getPenerimaanByTglPage(@PathVariable("namaSupp")String namaSupp,
                                              @PathVariable("tgl1")String tgl1,
                                              @PathVariable("tgl2")String tgl2,
                                              @PathVariable("hal")int hal,
                                              @PathVariable("jumlah")int jumlah){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tglAwal= sdf1.parse(tgl1);
            Date tglAkhir = sdf2.parse(tgl2);
            return penerimaanHdService.getByNamaSuppTgl(namaSupp, tglAwal, tglAkhir, hal, jumlah);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "new",method = RequestMethod.GET)
    public PenerimaanHd  getNew() {
        return new PenerimaanHd();
    }

    @RequestMapping(method = RequestMethod.POST)
    PenerimaanHd save(@RequestBody PenerimaanHdDto penerimaanHdDto) throws SupplierTidakValidException{

        return penerimaanHdService.save(penerimaanHdDto);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "approve/{idHd}/idGudang/{idGudang}")
    PenerimaanHd approve(@PathVariable("idHd")Long idHd,
                         @PathVariable("idGudang")Long idGudang) {

        return penerimaanHdService.approve(idHd, idGudang);
    }

    @RequestMapping(method = RequestMethod.PUT,
                    value = "{idEdit}")
    PenerimaanHd save(@PathVariable("idEdit")Long idEdit, @RequestBody PenerimaanHdDto penerimaanHdDto){

        return penerimaanHdService.update(idEdit, penerimaanHdDto);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public PenerimaanHd  getById(@PathVariable("id")Long id)
    {
        return penerimaanHdService.getById(id);
    }

    @RequestMapping(value = "total/{id}",
            method = RequestMethod.GET)
    Double totalBelanja(@PathVariable("id")Long id){
        return penerimaanDtService.total(id);
    }


    @RequestMapping(value = "/report/{id}",method = RequestMethod.GET)
    public void preview(@PathVariable("id")Long id,HttpServletResponse response)
    {
        List<ReportBuktiTerimaDto> reportBuktiTerimaDto = penerimaanHdService.reportBuktiTerima(id);
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/transaksi/BuktiPenerimaan.jasper",maps,reportBuktiTerimaDto,"Bukti Terima",response);

    }

    @RequestMapping(value = "/idSupp/{idSupp}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}",
            method = RequestMethod.GET)
    ResponsePembayaranSupplierDto getPenerimaanApproveByTglPage(@PathVariable("idSupp")Long idSupp,
                                                     @PathVariable("tgl1")String tgl1,
                                                     @PathVariable("tgl2")String tgl2,
                                                     @PathVariable("hal")int hal,
                                                     @PathVariable("jumlah")int jumlah){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tglAwal= sdf1.parse(tgl1);
            Date tglAkhir = sdf2.parse(tgl2);
            Page<PenerimaanHd> penerimaanHdPage = penerimaanHdService.getByIdSuppTglApproved(idSupp, tglAwal, tglAkhir, hal, jumlah);
            Iterator<PenerimaanHd> penerimaanHds = penerimaanHdPage.iterator();
            List<PembayaranSupplierDto> pembayaranSupplierDtos = new ArrayList<PembayaranSupplierDto>();
            while (penerimaanHds.hasNext()){
                PenerimaanHd penerimaanHd = penerimaanHds.next();
                PembayaranSupplierDto pembayaranSupplierDto = new PembayaranSupplierDto();
                pembayaranSupplierDto.setTotal(penerimaanDtService.total(penerimaanHd.getId()));
                pembayaranSupplierDto.setId(penerimaanHd.getId());
                pembayaranSupplierDto.setKeterangan(penerimaanHd.getKeterangan());
                pembayaranSupplierDto.setNamaSupplier(penerimaanHd.getSupplier().getNama());
                pembayaranSupplierDto.setNoFaktur(penerimaanHd.getNoFaktur());
                pembayaranSupplierDto.setTglTerima(penerimaanHd.getTglTerima());
                pembayaranSupplierDtos.add(pembayaranSupplierDto);

            }
            ResponsePembayaranSupplierDto responsePembayaranSupplierDto = new ResponsePembayaranSupplierDto();
            responsePembayaranSupplierDto.setContent(pembayaranSupplierDtos);
            responsePembayaranSupplierDto.setTotalElements(penerimaanHdPage.getTotalElements());
            return responsePembayaranSupplierDto;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.POST,
                    value = "pembayaran")
    PenerimaanHd approve(@RequestBody DataPembayaranSupplierDto dataPembayaranSupplierDto) {

        return penerimaanHdService.pembayaran(dataPembayaranSupplierDto);
    }



}
