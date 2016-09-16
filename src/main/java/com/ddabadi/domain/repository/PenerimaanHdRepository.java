package com.ddabadi.domain.repository;

import com.ddabadi.domain.DashboardTopPenjualan;
import com.ddabadi.domain.PenerimaanHd;
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
 * Created by deddy on 5/25/16.
 */
public interface PenerimaanHdRepository extends JpaRepository<PenerimaanHd,Long> {

    Page<PenerimaanHd> findBySupplierNamaLikeAndTglTerimaBetween(String supplierNama, Date tgl1, Date tgl2, Pageable pageable);
    Page<PenerimaanHd> findBySupplierAndTglTerimaBetweenAndStatusTerima(Supplier supplier, Date tgl1, Date tgl2, StatusTerima statusTerima, Pageable pageable);


    //LAPORAN
    List<PenerimaanHd> findByTglTerimaBetweenOrderByTglTerimaAsc(Date tgl1, Date tgl2);
    List<PenerimaanHd> findByTglBayarBetweenAndStatusTerimaOrderByTglBayarAsc(Date tgl1, Date tgl2, StatusTerima statusTerima);

    //dashboard penjualan-pembelian
    List<PenerimaanHd> findByTglTerimaBetweenAndStatusTerima(Date tgl1, Date tgl2, StatusTerima statusTerima);

    // dash board
    @Query(value = " select new com.ddabadi.domain.DashboardTopPenjualan(hd.supplier.nama, sum(hd.total)  )" +
            " from PenerimaanHd hd " +
            " where hd.tglTerima between :tgl1 and :tgl2 " +
            " and hd.statusTerima in ( com.ddabadi.enumera.StatusTerima.APPROVED, com.ddabadi.enumera.StatusTerima.PAID)" +
            " group by hd.supplier " +
            " order by sum(hd.total)  ")
        //" where d.penjualanHd.statusTerima = com.ddabadi.enumera.StatusTerima.APPROVED " +
    List<DashboardTopPenjualan> findByTopSupplier(@Param("tgl1")Date tgl1,
                                                  @Param("tgl2")Date tgl2);

}
