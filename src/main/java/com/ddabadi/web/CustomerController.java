package com.ddabadi.web;

import com.ddabadi.domain.Customer;
import com.ddabadi.dto.CekNamaDto;
import com.ddabadi.report.ReportController;
import com.ddabadi.service.CustomerService;
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
@RequestMapping(value = "api/customer", produces = "application/json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private Logger logger= Logger.getLogger(CustomerController.class);

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    Customer getById(@PathVariable("id")Long id){
        logger.info("get by id");

        return customerService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Customer getById(@RequestBody Customer customer){

        logger.info("save");
        customer.setSisa(0D);
        customer.setIsTutup(false);
        return customerService.save(customer);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    Customer edit(@PathVariable("id")Long id,
                  @RequestBody Customer customer){
        logger.info("edit");

        return customerService.update(id, customer);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "new")
    Customer newRecord(){
        logger.info("new rec");

        return new Customer();
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
            produces = "application/json",
            method = RequestMethod.GET)
    Page<Customer> getByNama(@PathVariable("nama")String nama,
                             @PathVariable("hal")int hal,
                             @PathVariable("jumlah")int jumlah){
        logger.info("get nama page, nama = " + nama);
        if (nama.equals("--")){
            return customerService.getByNama("%",hal,jumlah);
        }else{
            return customerService.getByNama("%"+nama+"%",hal,jumlah);
        }
    }


    @RequestMapping(method = RequestMethod.GET,
            value = "report")
    public void previewLap(HttpServletResponse response){

        List<Customer> customers = customerService.getAll();
        Map<String,Object> maps = new HashMap<String, Object>();
        ReportController report = new ReportController();

        report.previewReport("/report/master/masterCustomer.jasper", maps, customers, "Laporan", response);

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "isNamaExist")
    Boolean isNamaAda(@RequestBody CekNamaDto cekNamaDto){

        return customerService.isNamaAda(cekNamaDto.getNama());
    }

}
