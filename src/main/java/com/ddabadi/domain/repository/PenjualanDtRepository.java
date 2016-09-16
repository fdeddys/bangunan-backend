package com.ddabadi.domain.repository;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenjualanDt;
import com.ddabadi.domain.PenjualanHd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */
public interface PenjualanDtRepository extends JpaRepository<PenjualanDt,Long> {

    Page<PenjualanDt> findByPenjualanHd(PenjualanHd penjualanHd, Pageable pageable);


    List<PenjualanDt> findByPenjualanHdCustomerAndPenjualanHdIsTransBarangTrue(Customer customer);
    List<PenjualanDt> findByPenjualanHdCustomerAndPenjualanHdIsTransBarangFalse(Customer customer);

    // approve -batal approve -report(nota) -hitungTotal
    List<PenjualanDt> findAllByPenjualanHdAndPenjualanHdIsTransBarangTrue(PenjualanHd penjualanHd);
    List<PenjualanDt> findAllByPenjualanHdAndPenjualanHdIsTransBarangFalse(PenjualanHd penjualanHd);

}
