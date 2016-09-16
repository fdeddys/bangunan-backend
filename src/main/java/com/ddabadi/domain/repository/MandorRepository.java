package com.ddabadi.domain.repository;

import com.ddabadi.domain.Mandor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deddy on 8/30/16.
 */

public interface MandorRepository extends JpaRepository<Mandor,Long> {

    Page<Mandor> findByNamaLike(String nama, Pageable pageable);

}
