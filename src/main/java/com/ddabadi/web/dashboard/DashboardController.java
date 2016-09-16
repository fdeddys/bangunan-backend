package com.ddabadi.web.dashboard;

import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.exception.TanggalTidakValidException;
import com.ddabadi.service.DashBoardService;
import com.ddabadi.service.PenerimaanHdService;
import com.ddabadi.service.PenjualanDtService;
import com.ddabadi.service.PenjualanHdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by deddy on 6/29/16.
 */

@RestController
@RequestMapping(value = "api/dashboard")
public class DashboardController {

    @Autowired private DashBoardService dashBoardService;
    @Autowired private PenerimaanHdService penerimaanHdService;
    @Autowired private PenjualanHdService penjualanHdService;

    @RequestMapping(value = "penerimaan/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}",
                    method = RequestMethod.GET)
    Double getTotalPenerimaan(@PathVariable("tglAwal")String tglAwal,
                              @PathVariable("tglAkhir")String tglAkhir){

        Date tgl1;
        Date tgl2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            tgl1 = sdf.parse(tglAwal);
            tgl2 = sdf.parse(tglAkhir);
            return  dashBoardService.getPenerimaanByDate(tgl1, tgl2);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new TanggalTidakValidException("error");

        }
    }

    @RequestMapping(value = "penjualan/tglAwal/{tglAwal}/tglAkhir/{tglAkhir}",
            method = RequestMethod.GET)
    Double getTotalPenjualan(@PathVariable("tglAwal")String tglAwal,
                             @PathVariable("tglAkhir")String tglAkhir){

        Date tgl1;
        Date tgl2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            tgl1 = sdf.parse(tglAwal);
            tgl2 = sdf.parse(tglAkhir);
            return  dashBoardService.getPenjualanByDate(tgl1, tgl2);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new TanggalTidakValidException("error");

        }
    }

    @RequestMapping(value = "tglAkhir",
                    method = RequestMethod.GET)
    String getTglAkhir(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(calendar.getTime()).toString().trim();
    }

    @RequestMapping(value = "tglAwal",
                    method = RequestMethod.GET)
    Map<String, Object> getTglAwal(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.DATE, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tglAwal = sdf.format(calendar.getTime());

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        String tglAkhir = sdf.format(calendar.getTime());
        //System.out.println("hasil = [" + hasil +"]");
        Map<String, Object>map= new HashMap<String, Object>();
        map.put("tglAwal", tglAwal);
        map.put("tglAkhir", tglAkhir);
        return  map;
    }



    @RequestMapping(value = "topCustomer/{tglAwal}/tglAkhir/{tglAkhir}",
                    method = RequestMethod.GET)
    List<DashboardTopPenjualan> getTotalTopCustomer(@PathVariable("tglAwal")String tglAwal,
                                     @PathVariable("tglAkhir")String tglAkhir){
//List<DashboardTopPenjualan>
        //List<Map<String,Double>>
        Date tgl1;
        Date tgl2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            tgl1 = sdf.parse(tglAwal);
            tgl2 = sdf.parse(tglAkhir);
            //List<DashboardTopPenjualan> penjualanObj = penjualanDtService.getTopCustomer(tgl1, tgl2);
            List<DashboardTopPenjualan>  penjualanObj = penjualanHdService.getTopCustByTanggalJual(tgl1, tgl2);

//            Iterator<DashboardTopPenjualan> dashboardTopPenjualans= penjualanObj.iterator();
//            List<Map<String,Double>> maps = new ArrayList<Map<String,Double>>();
//            while(dashboardTopPenjualans.hasNext()){
//                DashboardTopPenjualan dasboard = dashboardTopPenjualans.next();
//                Map<String,Double> map = new HashMap<String ,Double>();
//                map.put(dasboard.getLabel(),dasboard.getValue());
//                maps.add(map);
//            }
            //.subList(0, 5);

//            Map<String,Double> maps = new HashMap<String, Double>();
//            maps.put(penjualanObj[0]);
//            for (Object  result : penjualanObj) {
//                System.out.println(result[0]);
//                Object hasil =  ((Object)result).nama;

//                Integer jumlah= (Integer)result[1];
//                Long count = (Long)result[2];
//            }
//            System.out.println(penjualanObj.get(1)[0][0]);
            return penjualanObj;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new TanggalTidakValidException("error");

        }
    }



    @RequestMapping(value = "topSupplier/{tglAwal}/tglAkhir/{tglAkhir}",
                    method = RequestMethod.GET)
    List<DashboardTopPenjualan> getTotalTopSupplier(@PathVariable("tglAwal")String tglAwal,
                                                    @PathVariable("tglAkhir")String tglAkhir){

        Date tgl1;
        Date tgl2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            tgl1 = sdf.parse(tglAwal);
            tgl2 = sdf.parse(tglAkhir);

            List<DashboardTopPenjualan>  penerimaans = penerimaanHdService.getTopSuppByTanggalJual(tgl1, tgl2);

            return penerimaans;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new TanggalTidakValidException("error");

        }
    }

    //END
}
