package com.ddabadi.domain.repository;

import com.ddabadi.domain.ReturSupplierHd;
import com.ddabadi.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/10/16.
 */
public interface ReturSupplierHdRepository extends JpaRepository<ReturSupplierHd,Long> {

    Page<ReturSupplierHd> findBySupplierNamaLikeAndTglReturBetween(String nama, Date tgl1, Date tgl2, Pageable pageable);

    //LAPORAN
    List<ReturSupplierHd> findByTglReturBetween(Date tgl1, Date tgl2);
    List<ReturSupplierHd> findBySupplierAndTglReturBetween(Supplier supplier,Date tgl1, Date tgl2);

}
