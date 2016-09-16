package com.ddabadi.domain.repository;

import com.ddabadi.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 5/27/16.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Page<Customer> findByNamaLike(String nama, Pageable pageable);
    List<Customer> findByNama(String nama);

}
