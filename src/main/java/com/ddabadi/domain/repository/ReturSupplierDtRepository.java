package com.ddabadi.domain.repository;

import com.ddabadi.domain.ReturSupplierDt;
import com.ddabadi.domain.ReturSupplierHd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 6/10/16.
 */
public interface ReturSupplierDtRepository extends JpaRepository<ReturSupplierDt,Long> {

    Page<ReturSupplierDt> findByReturSupplierHd(ReturSupplierHd returSupplierHd, Pageable pageable);
    List<ReturSupplierDt> findByReturSupplierHd(ReturSupplierHd returSupplierHd);

}
