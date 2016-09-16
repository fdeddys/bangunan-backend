package com.ddabadi.domain.repository;

import com.ddabadi.domain.CaraBayar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by deddy on 6/12/16.
 */
public interface CaraBayarRepository extends JpaRepository<CaraBayar, Long> {

    Page<CaraBayar> findByNama(String nama, Pageable pageable);
}
