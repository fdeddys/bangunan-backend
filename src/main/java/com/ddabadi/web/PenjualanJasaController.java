package com.ddabadi.web;

import com.ddabadi.domain.PenjualanJasa;
import com.ddabadi.dto.PenjualanJasaDto;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.exception.TanggalTidakValidException;
import com.ddabadi.service.PenjualanJasaService;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deddy on 9/4/16.
 */

@RestController
@RequestMapping(value = "api/penjualanJasa")
public class PenjualanJasaController {


    @Autowired private PenjualanJasaService penjualanJasaService;

    @RequestMapping(value = "{id}",
                    produces = "application/json",
                    method = RequestMethod.GET)
    PenjualanJasa getById(@PathVariable("id")Long id){

        return penjualanJasaService.getById(id);
    }

    @RequestMapping(value = "new",
            produces = "application/json",
            method = RequestMethod.GET)
    PenjualanJasaDto getNew(){

        return new PenjualanJasaDto();
    }


    @RequestMapping(method = RequestMethod.POST)
    PenjualanJasa getById(@RequestBody PenjualanJasaDto penjualanJasaDto){
       return penjualanJasaService.save(penjualanJasaDto);

    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT)
    PenjualanJasa edit(@PathVariable("id")Long id,
                       @RequestBody PenjualanJasa penjualanJasa){

        return penjualanJasaService.update(id, penjualanJasa);
    }


    @RequestMapping(value = "/nama/{nama}/tgl1/{tglAwal}/tgl2/{tglAkhir}/hal/{hal}/jumlah/{jumlah}",
                    produces = "application/json",
                    method = RequestMethod.GET)
    Page<PenjualanJasa> getByTgl(@PathVariable("nama")String nama,
                                 @PathVariable("tglAwal")String tglAwal,
                                 @PathVariable("tglAkhir")String tglAkhir,
                                 @PathVariable("hal")Integer hal,
                                 @PathVariable("jumlah")Integer jumlah){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tgl1 = sdf.parse(tglAwal);
            Date tgl2 = sdf.parse(tglAkhir);
            return penjualanJasaService.getByNamaCustTglTerima("%"+nama+"%",tgl1,tgl2, hal, jumlah);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
