package com.ddabadi.web;

import com.ddabadi.domain.ReturSupplierDt;
import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.dto.ReturDtDto;
import com.ddabadi.dto.ReturHdDto;
import com.ddabadi.service.ReturSupplierDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by deddy on 6/11/16.
 */

@RestController
@RequestMapping(value = "api/returDt")
public class ReturDtController {

    @Autowired private ReturSupplierDtService returSupplierDtService;

    @RequestMapping(value = "{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")Long id){

        returSupplierDtService.delete(id);
    }

    @RequestMapping(value = "idHd/{idHd}/hal/{hal}/jumlah/{jumlah}",
                    produces = "application/json",
                    method = RequestMethod.GET)
    Page<ReturSupplierDt> getByNama(@PathVariable("idHd")Long idHd,
                                    @PathVariable("hal")int hal,
                                    @PathVariable("jumlah")int jumlah){

        return returSupplierDtService.getByIdHd(idHd,hal,jumlah);
    }

    @RequestMapping(method = RequestMethod.POST)
    ReturSupplierDt save(@RequestBody ReturDtDto returDtDto) {

        return returSupplierDtService.save(returDtDto);
    }




}
