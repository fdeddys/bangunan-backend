package com.ddabadi.web;

import com.ddabadi.domain.Barang;
import com.ddabadi.dto.CekNamaDto;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.BarangService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deddy on 5/27/16.
 */

@RestController
@RequestMapping(value = "api/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;
    private Logger logger= Logger.getLogger(BarangController.class);

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    Barang getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return barangService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Barang getById(@RequestBody Barang barang){
        logger.info("save");

        return barangService.save(barang);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    Barang edit(@PathVariable("id")Long id,
                @RequestBody Barang barang){
        logger.info("edit");

        return barangService.update(id, barang);
    }

    @RequestMapping(method = RequestMethod.POST,
                    value = "isNamaExist")
    Boolean isNamaAda(@RequestBody CekNamaDto cekNamaDto){

        return barangService.isNamaAda(cekNamaDto.getNama());
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "new")
    Barang newRecord(){
        logger.info("new rec");

        return new Barang();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}/isBarang",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Barang> getByNama(@PathVariable("nama")String nama,
                             @PathVariable("hal")int hal,
                             @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return barangService.getByNamaIsBarang("%",hal,jumlah);
        }else{
            return barangService.getByNamaIsBarang("%"+nama+"%",hal,jumlah);
        }
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}/isJasa",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Barang> getJasaByNama(@PathVariable("nama")String nama,
                               @PathVariable("hal")int hal,
                               @PathVariable("jumlah")int jumlah){
        logger.info("get jasa - nama page, nama = " + nama);
        if (nama.equals("--")){
            return barangService.getByNamaIsJasa("%", hal, jumlah);
        }else{
            return barangService.getByNamaIsJasa("%" + nama + "%", hal, jumlah);
        }
    }


    @RequestMapping(method = RequestMethod.GET,
            value = "report")
    public void previewLap(HttpServletResponse response){

        List<Barang> barangs = barangService.getAll();
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/master/masterBarang.jasper",maps,barangs,"Laporan",response);

    }
}
