package com.ddabadi.web;

import com.ddabadi.domain.PenerimaanDt;
import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.repository.PenerimaanDtRepository;
import com.ddabadi.dto.PenerimaanDtDto;
import com.ddabadi.exception.SupplierTidakValidException;
import com.ddabadi.service.PenerimaanDtService;
import com.ddabadi.service.PenerimaanHdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by deddy on 6/1/16.
 */

@RestController
@RequestMapping(value = "api/penerimaanDt",
                produces = "application/json")
public class PenerimaanDtController {

    @Autowired private PenerimaanDtService penerimaanDtService;

    @RequestMapping(value = "new",
                    method = RequestMethod.GET)
    PenerimaanDt getNew(){
        return new PenerimaanDt();
    }

    @RequestMapping(method = RequestMethod.POST)
    PenerimaanDt save(@RequestBody PenerimaanDtDto penerimaanDtDto) {

        return penerimaanDtService.save(penerimaanDtDto);
    };

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE)
    void delete(@PathVariable("id")Long id){
        penerimaanDtService.delete(id);
    }




    @RequestMapping(value = "idHd/{idHd}/hal/{hal}/jumlah/{jumlah}",
            method = RequestMethod.GET)
    Page<PenerimaanDt> getByIdHd(@PathVariable("idHd")Long idHd,
                                 @PathVariable("hal")int hal,
                                 @PathVariable("jumlah")int jumlah){

        return penerimaanDtService.getByIdHd(idHd,hal,jumlah);
    }

}
