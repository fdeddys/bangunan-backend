package com.ddabadi.web;

import com.ddabadi.domain.Supplier;
import com.ddabadi.dto.CekNamaDto;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.SupplierService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deddy on 5/23/16.
 */

@RestController
@RequestMapping(value = "api/supplier")
public class SupplierController {

    @Autowired private SupplierService supplierService;
    private Logger logger= Logger.getLogger(SupplierController.class);

    @RequestMapping(value = "{id}",
                    produces = "application/json",
                    method = RequestMethod.GET)
    Supplier getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return supplierService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Supplier getById(@RequestBody Supplier supplier){
        logger.info("save");

        return supplierService.save(supplier);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT)
    Supplier edit(@PathVariable("id")Long id,
                  @RequestBody Supplier supplier){
        logger.info("edit");

        return supplierService.update(id, supplier);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "new")
    Supplier newRecord(){
        logger.info("new rec");

        return new Supplier();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Supplier> getByNama(@PathVariable("nama")String nama,
                             @PathVariable("hal")int hal,
                             @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return supplierService.getByNama("%",hal,jumlah);
        }else{
            return supplierService.getByNama("%"+nama+"%",hal,jumlah);
        }
    }


    @RequestMapping(method = RequestMethod.GET,
                    value = "report")
    public void previewLap(HttpServletResponse response){

        List<Supplier> suppliers = supplierService.getAll();
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/master/masterSupplier.jasper", maps, suppliers, "Laporan", response);

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "isNamaExist")
    Boolean isNamaAda(@RequestBody CekNamaDto cekNamaDto){

        return supplierService.isNamaAda(cekNamaDto.getNama());
    }

}
