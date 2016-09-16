package com.ddabadi.web;

import com.ddabadi.domain.HistoryStock;
import com.ddabadi.service.HistoryStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/10/16.
 */

@RestController
@RequestMapping(value = "api/historyBarang",
                produces = "application/json")
public class HistoryBarangController {

    @Autowired private HistoryStockService historyStockService;

    @RequestMapping(value = "barang/{idBarang}/gudang/{idGudang}/tgl1/{tgl1}/tgl2/{tgl2}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    Page<HistoryStock> getHistoryPage(@PathVariable("idBarang")Long idBarang,
                                      @PathVariable("idGudang")Long idGudang,
                                      @PathVariable("tgl1")String tgl1,
                                      @PathVariable("tgl2")String tgl2,
                                      @PathVariable("hal")int hal,
                                      @PathVariable("jumlah")int jumlah){

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date tglAwal = sdf1.parse(tgl1);
            Date tglAkhir = sdf1.parse(tgl2);
            return historyStockService.getByBarangGudangTanggal(idBarang,idGudang, tglAwal, tglAkhir, hal, jumlah);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }
}
