package com.ddabadi.domain.repository;

import com.ddabadi.domain.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */
public interface BarangRepository extends JpaRepository<Barang,Long> {

    Page<Barang> findByNamaLikeAndJasaIsFalse(String nama, Pageable pageable);
    Page<Barang> findByNamaLikeAndJasaIsTrue(String nama, Pageable pageable);
    List<Barang> findByNama(String nama);

}
