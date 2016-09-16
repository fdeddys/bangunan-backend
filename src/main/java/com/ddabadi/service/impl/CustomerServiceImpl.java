package com.ddabadi.service.impl;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.repository.CustomerRepository;
import com.ddabadi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by deddy on 5/27/16.
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired private CustomerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return repository.saveAndFlush(customer);
    }

    @Override
    public Customer update(Long idUpdate, Customer customer) {
        Customer customerUpd = repository.findOne(idUpdate);
        customerUpd.setNama(customer.getNama());
        customerUpd.setIsTutup(customer.getIsTutup());
        customerUpd.setKeterangan(customer.getKeterangan());
        customerUpd.setMaxLimit(customer.getMaxLimit());
        customerUpd.setSisa(customer.getSisa());
        customerUpd.setMaxLimitJasa(customer.getMaxLimitJasa());
        customerUpd.setSisaJasa(customer.getSisaJasa());
        return repository.saveAndFlush(customerUpd);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> getByNama(String nama, int hal, int jumlah) {
        PageRequest pageRequest= new PageRequest(hal -1, jumlah, Sort.Direction.ASC,"nama");
        return repository.findByNamaLike("%"+nama+"%",pageRequest);
    }

    @Override
    public Boolean isNamaAda(String nama) {
        List<Customer> customers = repository.findByNama(nama);

        if (customers.size()>0){
            return  true;
        }else{
            return false;
        }
    }

}
