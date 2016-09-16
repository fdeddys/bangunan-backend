package com.ddabadi.domain.repository;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.HistoryCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by deddy on 6/8/16.
 */
public interface HistoryCustomerRepository extends JpaRepository<HistoryCustomer,Long> {

    Page<HistoryCustomer> findByCustomerAndTglTransaksiBetween(Customer customer, Date tglAwal, Date tglAkhir, Pageable pageable);

    Page<HistoryCustomer> findByCustomer(Customer customer, Pageable pageable);
}
