package com.ddabadi.service;

import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenjualanDt;
import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.dto.PenjualanDtDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */
public interface PenjualanDtService {

    PenjualanDt getById(Long id);
    void delete(Long id);
    PenjualanDt save(PenjualanDtDto penjualanDtDto);
    Page<PenjualanDt> getByPenjualanHd(Long penjualanHdId, int hal, int jumlah);
    List<PenjualanDt> getAllByPenjualanHdBarang(Long penjualanHdId);
    List<PenjualanDt> getAllByPenjualanHdJasa(Long penjualanHdId);
    Double totalBarangByCustomer(Long idCustomer);
    Double totalJasaByCustomer(Long idCustomer);
    List<DashboardTopPenjualan> getTopCustomer(Date tgl1,Date tgl2);

}
