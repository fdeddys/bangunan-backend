package com.ddabadi.web;

import com.ddabadi.domain.HistoryCustomer;
import com.ddabadi.service.HistoryCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deddy on 6/10/16.
 */

@RestController
@RequestMapping(value = "api/historyCustomer", produces = "application/json")
public class HistoryCustomerController {

    @Autowired private HistoryCustomerService historyCustomerService;

    @RequestMapping(value = "/customer/{idCustomer}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    Page<HistoryCustomer> getHistory(@PathVariable("idCustomer")Long idCustomer,
                                     @PathVariable("tgl1")String tgl1,
                                     @PathVariable("tgl2")String tgl2,
                                     @PathVariable("hal")int hal,
                                     @PathVariable("jumlah")int jumlah){
        Boolean allRec;
        if(tgl1.equals("--")){
            allRec=true;
        }else{
            allRec=false;
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tglAwal = sdf1.parse(tgl1);
            Date tglAkhir = sdf2.parse(tgl2);
            return historyCustomerService.getByCustomerIdtglTransaksi(idCustomer,tglAwal,tglAkhir,hal,jumlah,allRec);
        } catch (ParseException e) {
            e.printStackTrace();
            return  null;
        }


    }

}
