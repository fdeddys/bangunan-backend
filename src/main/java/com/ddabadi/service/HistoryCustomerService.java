package com.ddabadi.service;

import com.ddabadi.domain.HistoryCustomer;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by deddy on 6/8/16.
 */
public interface HistoryCustomerService {


    HistoryCustomer save(HistoryCustomer historyCustomer);
    Page<HistoryCustomer> getByCustomerIdtglTransaksi(Long idCust, Date tglAwal, Date tglAkhir, int hal, int jumlah, Boolean allData);

}
