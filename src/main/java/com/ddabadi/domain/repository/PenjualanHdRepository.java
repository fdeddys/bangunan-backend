package com.ddabadi.domain.repository;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenjualanHd;
import com.ddabadi.domain.Supplier;
import com.ddabadi.enumera.StatusTerima;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by deddy on 6/7/16.
 */
public interface PenjualanHdRepository extends JpaRepository<PenjualanHd, Long> {

    Page<PenjualanHd> findByCustomerNamaLikeAndTglJualBetweenAndIsTransBarangTrue(String nama, Date tgl1, Date tgl2, Pageable pageable);
    Page<PenjualanHd> findByCustomerNamaLikeAndTglJualBetweenAndIsTransBarangFalse(String nama, Date tgl1, Date tgl2, Pageable pageable);
    Page<PenjualanHd> findByCustomerNamaLikeAndTglJualBetweenAndStatusJual(String namaCust, Date tgl1, Date tgl2, StatusTerima statusJual, Pageable pageable);

    //LAPORAN penjualan
    //Supp + Status
    List<PenjualanHd> findByCustomerAndStatusJualAndTglJualBetweenOrderByTglJualAsc(Customer customer, StatusTerima statusJual, Date tgl1, Date Tgl2);
    //Supp + status ALL
    List<PenjualanHd> findByCustomerAndTglJualBetweenOrderByTglJualAsc(Customer customer, Date tgl1, Date Tgl2);
    //non Supp + Status
    List<PenjualanHd> findByStatusJualAndTglJualBetweenOrderByTglJualAsc(StatusTerima statusJual, Date tgl1, Date Tgl2);
    //non Supp + status ALL
    List<PenjualanHd> findByTglJualBetweenOrderByTglJualAsc(Date tgl1, Date Tgl2);

    //laporan pembayaran
    List<PenjualanHd> findByCustomerAndTglBayarBetweenOrderByTglBayarAsc(Customer customer, Date tgl1, Date Tgl2);
    List<PenjualanHd> findByTglBayarBetweenOrderByTglBayarAsc(Date tgl1, Date Tgl2);

    //DashBoard
    List<PenjualanHd> findByTglJualBetweenAndStatusJual(Date tgl1, Date Tgl2, StatusTerima statusJual);

    // dash board
    @Query(value = " select new com.ddabadi.domain.DashboardTopPenjualan(hd.customer.nama, sum(hd.total)  )" +
            " from PenjualanHd hd " +
            " where hd.tglJual between :tgl1 and :tgl2 " +
            " and hd.statusJual in ( com.ddabadi.enumera.StatusTerima.APPROVED, com.ddabadi.enumera.StatusTerima.PAID)" +
            " group by hd.customer " +
            " order by sum(hd.total)  ")
    //" where d.penjualanHd.statusTerima = com.ddabadi.enumera.StatusTerima.APPROVED " +
    List<DashboardTopPenjualan> findByTopCustomer(@Param("tgl1")Date tgl1,
                                                  @Param("tgl2")Date tgl2);
}


