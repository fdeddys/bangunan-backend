package com.ddabadi.web;

import com.ddabadi.domain.Gudang;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.GudangService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deddy on 6/5/16.
 */

@RestController
@RequestMapping(value = "api/gudang", produces = "application/json")
public class GudangController {

    @Autowired
    private GudangService gudangService;
    private Logger logger= Logger.getLogger(GudangController.class);

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    Gudang getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return gudangService.getById(id);
    }

    @RequestMapping(value = "all",
            produces = "application/json",
            method = RequestMethod.GET)
    List<Gudang> getAll(){
        logger.info("get all");

        return gudangService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    Gudang getById(@RequestBody Gudang gudang){
        logger.info("save");

        return gudangService.save(gudang);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    Gudang edit(@PathVariable("id")Long id,
                @RequestBody Gudang gudang){
        logger.info("edit");

        return gudangService.update(id, gudang);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "new")
    Gudang newRecord(){
        logger.info("new rec");

        return new Gudang();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Gudang> getByNama(@PathVariable("nama")String nama,
                           @PathVariable("hal")int hal,
                           @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return gudangService.getByNama("%",hal,jumlah);
        }else{
            return gudangService.getByNama("%"+nama+"%",hal,jumlah);
        }
    }


    @RequestMapping(method = RequestMethod.GET,
            value = "report")
    public void previewLap(HttpServletResponse response){

        List<Gudang> gudangs = gudangService.getAll();
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/master/masterGudang.jasper",maps,gudangs,"Laporan",response);

    }
}
