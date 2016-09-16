package com.ddabadi.service;

import com.ddabadi.domain.PenjualanJasa;
import com.ddabadi.dto.PenjualanJasaDto;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by deddy on 9/4/16.
 */
public interface PenjualanJasaService {

    PenjualanJasa getById(Long id);
    PenjualanJasa save(PenjualanJasaDto penjualanJasaDto);
    PenjualanJasa update(Long idUpdate, PenjualanJasa penjualanJasa);
    PenjualanJasa approve(Long id, Long idGudang);
    PenjualanJasa batalApprove(Long id);
    Page<PenjualanJasa> getByNamaCustTglTerima(String namaCust, Date tgl1, Date tgl2, Integer hal, Integer jumlah);

}
