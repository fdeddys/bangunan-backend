package com.ddabadi.web;

import com.ddabadi.domain.Satuan;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.SatuanService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deddy on 5/29/16.
 */
@RestController
@RequestMapping(value = "api/satuan", produces = "application/json")
public class SatuanController {

    @Autowired
    private SatuanService satuanService;
    private Logger logger= Logger.getLogger(SatuanController.class);

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    Satuan getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return satuanService.getById(id);
    }

    @RequestMapping(value = "all",
            produces = "application/json",
            method = RequestMethod.GET)
    List<Satuan> getAll(){
        logger.info("get all");

        return satuanService.getALl();
    }

    @RequestMapping(method = RequestMethod.POST)
    Satuan getById(@RequestBody Satuan satuan){
        logger.info("save");

        return satuanService.save(satuan);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    Satuan edit(@PathVariable("id")Long id,
                  @RequestBody Satuan satuan){
        logger.info("edit");

        return satuanService.update(id, satuan);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "new")
    Satuan newRecord(){
        logger.info("new rec");

        return new Satuan();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Satuan> getByNama(@PathVariable("nama")String nama,
                             @PathVariable("hal")int hal,
                             @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return satuanService.getByNama("%",hal,jumlah);
        }else{
            return satuanService.getByNama("%"+nama+"%",hal,jumlah);
        }
    }


    @RequestMapping(method = RequestMethod.GET,
            value = "report")
    public void previewLap(HttpServletResponse response){

        List<Satuan> satuans = satuanService.getALl();
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/master/masterSatuan.jasper",maps,satuans,"Laporan",response);

    }
}
