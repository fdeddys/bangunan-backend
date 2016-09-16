package com.ddabadi.domain.repository;

import com.ddabadi.domain.Satuan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deddy on 5/24/16.
 */
public interface SatuanRepository extends JpaRepository<Satuan,Long> {

    Page<Satuan> findByNamaLike(String nama, Pageable pageable);
}
