package com.ddabadi.web;

import com.ddabadi.domain.Mandor;
import com.ddabadi.service.MandorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by deddy on 8/30/16.
 */

@RestController
@RequestMapping(value = "api/mandor", produces = "application/json")
public class MandorController {

    @Autowired private MandorService mandorService;
    private Logger logger = Logger.getLogger(MandorController.class);

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    Mandor getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return mandorService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Mandor getById(@RequestBody Mandor mandor){
        logger.info("save");

        return mandorService.save(mandor);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    Mandor edit(@PathVariable("id")Long id,
                @RequestBody Mandor mandor){
        logger.info("edit");

        return mandorService.update(id,mandor);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "new")
    Mandor newRecord(){
        logger.info("new rec");

        return new Mandor();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Mandor> getByNama(@PathVariable("nama")String nama,
                           @PathVariable("hal")int hal,
                           @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return mandorService.getByNama("%",hal,jumlah);
        }else{
            return mandorService.getByNama("%"+nama+"%",hal,jumlah);
        }
    }



}
