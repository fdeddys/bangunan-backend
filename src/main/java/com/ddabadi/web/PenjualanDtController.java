package com.ddabadi.web;

import com.ddabadi.domain.PenjualanDt;
import com.ddabadi.dto.PenjualanDtDto;
import com.ddabadi.service.PenjualanDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by deddy on 6/7/16.
 */

@RestController
@RequestMapping(value = "api/penjualanDt", produces = "application/json")
public class PenjualanDtController {

    @Autowired
    private PenjualanDtService penjualanDtService;

    @RequestMapping(value = "new",
            method = RequestMethod.GET)
    PenjualanDt getNew(){
        return new PenjualanDt();
    }

    @RequestMapping(method = RequestMethod.POST)
    PenjualanDt save(@RequestBody PenjualanDtDto penjualanDtDto) {

        return penjualanDtService.save(penjualanDtDto);
    };

    @RequestMapping(value = "{id}",
            method = RequestMethod.DELETE)
    void delete(@PathVariable("id")Long id){
        penjualanDtService.delete(id);
    }

    @RequestMapping(value = "idHd/{idHd}/hal/{hal}/jumlah/{jumlah}",
            method = RequestMethod.GET)
    Page<PenjualanDt> getByIdHd(@PathVariable("idHd")Long idHd,
                                 @PathVariable("hal")int hal,
                                 @PathVariable("jumlah")int jumlah){

        return penjualanDtService.getByPenjualanHd(idHd,hal,jumlah);
    }

    @RequestMapping(value = "totalPenjualanByIdCust/{idCust}",
            method = RequestMethod.GET)
    Double getTotal(@PathVariable("idCust")Long idCust) {
        return penjualanDtService.totalBarangByCustomer(idCust);
    }


}
