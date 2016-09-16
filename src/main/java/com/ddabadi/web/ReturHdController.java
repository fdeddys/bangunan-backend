package com.ddabadi.web;

import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.dto.ReturHdDto;
import com.ddabadi.service.ReturSupplierDtService;
import com.ddabadi.service.ReturSupplierHdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deddy on 6/10/16.
 */

@RestController
@RequestMapping(value = "api/returHd", produces = "application/json")
public class ReturHdController {

    @Autowired private ReturSupplierHdService returSupplierHdService;
    @Autowired private ReturSupplierDtService returSupplierDtService;

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    ReturSupplierHd getById(@PathVariable("id")Long id){

        return returSupplierHdService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ReturSupplierHd getById(@RequestBody ReturHdDto returHdDto){

        return returSupplierHdService.save(returHdDto);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT)
    ReturSupplierHd edit(@PathVariable("id")Long id,
                @RequestBody ReturHdDto returHdDto){

        return returSupplierHdService.update(id, returHdDto);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "new")
    ReturSupplierHd newRecord(){
        return new ReturSupplierHd();
    }

    @RequestMapping(value = "nama/{nama}/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<ReturSupplierHd> getByNama(@PathVariable("nama")String nama,
                                    @PathVariable("tglAwal")String tglAwal,
                                    @PathVariable("tglAkhir")String tglAkhir,
                                    @PathVariable("hal")int hal,
                                    @PathVariable("jumlah")int jumlah){

        if(nama.equals("--")){
            nama ="%";
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tgl1 = sdf1.parse(tglAwal);
            Date tgl2 = sdf2.parse(tglAkhir);
            return returSupplierHdService.getByNamaTanggal("%" + nama + "%", tgl1, tgl2, hal, jumlah);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "total/{idHd}")
    Double getTotal(@PathVariable("idHd")Long id){
        return returSupplierDtService.total(id);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "approve/{idHd}/idGudang/{idGudang}")
    ReturSupplierHd approve(@PathVariable("idHd")Long idHd,
                            @PathVariable("idGudang")Long idGudang) {

        return returSupplierHdService.approve(idHd, idGudang);
    }

}
