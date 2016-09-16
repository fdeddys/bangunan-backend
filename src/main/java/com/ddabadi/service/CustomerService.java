package com.ddabadi.service;

import com.ddabadi.domain.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 5/27/16.
 */
public interface CustomerService {

    Customer getById(Long id);
    List<Customer> getAll();
    Customer save(Customer customer);
    Customer update(Long idUpdate, Customer customer);
    Page<Customer> getByNama(String nama, int hal, int jumlah);
    Boolean isNamaAda(String nama);
}
