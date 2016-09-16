package com.ddabadi.service;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.dto.DataPembayaranCustomerDto;
import com.ddabadi.dto.PenjualanHdDto;
import com.ddabadi.dto.ReportNotaPenjualanDto;
import com.ddabadi.enumera.StatusTerima;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */
public interface PenjualanHdService {

    PenjualanHd getById(Long id);
    PenjualanHd save(PenjualanHdDto penjualanHdDto);
    PenjualanHd update(Long idUpdate, PenjualanHdDto penjualanHdDto);
    PenjualanHd approve(Long id, Long idGudang);
    PenjualanHd batalApprove(Long id, Long idGudang);
    Page<PenjualanHd> getByNamaCustomerTanggalIsTransBarang(String nama, Date tgl1, Date tgl2, int hal, int jumlah);
    Page<PenjualanHd> getByNamaCustomerTanggalIsTransJasa(String nama, Date tgl1, Date tgl2, int hal, int jumlah);
    Double total(Long idHd);
    List<ReportNotaPenjualanDto> reportNotaPenjualan(Long idHd);

    Page<PenjualanHd> getByNamaCustTglApproved(String namaCust, Date tgl1, Date tgl2, int hal, int jumlah);
    PenjualanHd pembayaran(DataPembayaranCustomerDto dataPembayaranCustomerDto);


    //untuk laporan
    List<PenjualanHd> getByCustomerStatusJualTanggalJual(Long idCust, StatusTerima statusJual, Date tgl1, Date Tgl2);
    //untuk laporan pembayaran
    List<PenjualanHd> getByCustomerTanggalLunas(Long idCust, Date tgl1, Date tgl2);

    //dashboard
    Iterator<PenjualanHd> getByTanggalJualStatusJual(Date tgl1, Date tgl2, StatusTerima statusJual);

    List<DashboardTopPenjualan> getTopCustByTanggalJual(Date tgl1, Date tgl2);

}
