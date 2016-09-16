package com.ddabadi.service;

import com.ddabadi.domain.PenerimaanDt;
import com.ddabadi.dto.PenerimaanDtDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/26/16.
 */
public interface PenerimaanDtService {

    PenerimaanDt getById(Long id);
    PenerimaanDt save(PenerimaanDtDto penerimaanDtDto);
    PenerimaanDt update(Long idUpdate, PenerimaanDt penerimaanDt);
    Page<PenerimaanDt > getByIdHd(Long idHd, int hal, int jumlah);
    List<PenerimaanDt > getAllByIdHd(Long idHd);
    public void delete(Long id);
    Double total(Long id);
}
