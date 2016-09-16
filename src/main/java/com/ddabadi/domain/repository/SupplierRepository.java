package com.ddabadi.domain.repository;

import com.ddabadi.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 5/23/16.
 */
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    Page<Supplier> findByNamaLike(String nama, Pageable pageable);
    List<Supplier> findByNama(String nama);
}
