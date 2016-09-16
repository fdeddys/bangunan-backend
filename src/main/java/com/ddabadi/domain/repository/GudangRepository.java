package com.ddabadi.domain.repository;

import com.ddabadi.domain.Gudang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deddy on 5/24/16.
 */
public interface GudangRepository extends JpaRepository<Gudang, Long> {
    Page<Gudang> findByNamaLike(String nama,Pageable pageable);
}
