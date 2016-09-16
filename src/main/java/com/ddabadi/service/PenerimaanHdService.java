package com.ddabadi.service;

import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenerimaanHd;
import com.ddabadi.dto.DataPembayaranSupplierDto;
import com.ddabadi.dto.PenerimaanHdDto;
import com.ddabadi.dto.ReportBuktiTerimaDto;
import com.ddabadi.enumera.StatusTerima;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */
public interface PenerimaanHdService {

    PenerimaanHd getById(Long id);
    PenerimaanHd approve(Long id, Long idGudang);
    PenerimaanHd save(PenerimaanHdDto penerimaanHdDto);
    PenerimaanHd update(Long idUpdate, PenerimaanHdDto penerimaanHdDto);
    PenerimaanHd pembayaran(DataPembayaranSupplierDto dataPembayaranSupplierDto);
    Page<PenerimaanHd> getByNamaSuppTgl(String namaSupp, Date tgl1, Date tgl2, int hal, int jumlah);
    Page<PenerimaanHd> getByIdSuppTglApproved(Long idSupp, Date tgl1, Date tgl2, int hal, int jumlah);

    List<ReportBuktiTerimaDto> reportBuktiTerima(Long idHd);

    //untuk laporan penerimaanHD
    List<PenerimaanHd> getByTanggal(Date tgl1, Date tgl2);
    //untuk laporan pembayaran supplier
    List<PenerimaanHd> getByTanggalLunas(Date tgl1, Date tgl2, StatusTerima statusTerima);


    //dashboard
    Iterator<PenerimaanHd> getByTanggalBeliStatusTerima(Date tgl1, Date tgl2, StatusTerima statusTerima);
    List<DashboardTopPenjualan> getTopSuppByTanggalJual(Date tgl1, Date tgl2);
}
