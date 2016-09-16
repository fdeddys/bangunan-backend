package com.ddabadi.domain.repository;

import com.ddabadi.domain.Mandor;
import com.ddabadi.domain.PenjualanJasa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by deddy on 9/4/16.
 */
public interface PenjualanJasaRepository extends JpaRepository<PenjualanJasa,Long> {

    //untuk list HD jual Jasa
    Page<PenjualanJasa> findByTglTransaksiBetweenAndCustomerNamaLike(Date tgl1, Date tgl2, String namaCust, Pageable pageable);

}
