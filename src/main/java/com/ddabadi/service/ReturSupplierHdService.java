package com.ddabadi.service;

import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.domain.Supplier;
import com.ddabadi.dto.ReturHdDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/10/16.
 */
public interface ReturSupplierHdService {

    ReturSupplierHd getById(Long id);
    ReturSupplierHd save(ReturHdDto returHdDto);
    ReturSupplierHd update(Long idUpdate, ReturHdDto returHdDto);
    Page<ReturSupplierHd> getByNamaTanggal(String nama, Date tgl1, Date tgl2,  int hal, int jumlah);
    ReturSupplierHd approve(Long idReturHd, Long idGudang);
    List<ReturSupplierHd> getBySupplierTanggalRetur(Supplier supplier, Date tgl1, Date tgl2);

}
