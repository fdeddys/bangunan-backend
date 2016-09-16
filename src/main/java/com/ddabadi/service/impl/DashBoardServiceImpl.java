package com.ddabadi.service.impl;

import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.enumera.StatusTerima;
import com.ddabadi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/29/16.
 */

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired private PenjualanHdService penjualanHdService;
    @Autowired private PenerimaanHdService penerimaanHdService;
    @Autowired private PenerimaanDtService penerimaanDtService;
    @Autowired private PenjualanDtService penjualanDtService;

    @Override
    public Double getPenerimaanByDate(Date tgl1, Date tgl2) {

        // iterate status approve
        // iterate status paid
        Double totalApprove =  getPenerimaanByDateStatus(tgl1, tgl2, StatusTerima.APPROVED);
        Double totalPaid =  getPenerimaanByDateStatus(tgl1, tgl2, StatusTerima.PAID);

        return totalApprove + totalPaid;

    }

    @Override
    public Double getPenjualanByDate(Date tgl1, Date tgl2) {

        // iterate status approve
        // iterate status paid
        Double totalApprove =  getPenjualanByDateStatus(tgl1, tgl2, StatusTerima.APPROVED);
        Double totalPaid =  getPenjualanByDateStatus(tgl1, tgl2, StatusTerima.PAID);

        return totalApprove + totalPaid;
    };

    Double getPenjualanByDateStatus(Date tgl1, Date tgl2, StatusTerima statusJual){
        Double hasil=0D;
        Iterator<PenjualanHd> penjualanHds = penjualanHdService.getByTanggalJualStatusJual(tgl1, tgl2, statusJual);

        while (penjualanHds.hasNext()){
            PenjualanHd penjualanHd = penjualanHds.next();
            hasil = hasil + penjualanHdService.total(penjualanHd.getId());
        }
        return  hasil;
    }

    Double getPenerimaanByDateStatus(Date tgl1, Date tgl2, StatusTerima statusTerima){
        Double hasil=0D;
        Iterator<PenerimaanHd> penerimaanHds= penerimaanHdService.getByTanggalBeliStatusTerima(tgl1, tgl2, statusTerima);

        while (penerimaanHds.hasNext()){
            PenerimaanHd penerimaanHd = penerimaanHds.next();
            hasil = hasil + penerimaanDtService.total(penerimaanHd.getId());
        }
        return  hasil;
    }




}
