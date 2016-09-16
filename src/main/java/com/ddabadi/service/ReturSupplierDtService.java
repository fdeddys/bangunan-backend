package com.ddabadi.service;

import com.ddabadi.domain.ReturSupplierDt;
import com.ddabadi.dto.ReturDtDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 6/11/16.
 */
public interface ReturSupplierDtService {

    ReturSupplierDt getById(Long id);
    ReturSupplierDt save(ReturDtDto returDtDto);
    ReturSupplierDt update(Long idUpdate, ReturDtDto returDtDto);
    Page<ReturSupplierDt > getByIdHd(Long idHd, int hal, int jumlah);
    List<ReturSupplierDt > getByIdHdList(Long idHd);
    public void delete(Long id);
    Double total(Long id);

}
