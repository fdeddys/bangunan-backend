package com.ddabadi.service.impl;

import com.ddabadi.domain.Customer;
import com.ddabadi.domain.HistoryCustomer;
import com.ddabadi.domain.repository.HistoryCustomerRepository;
import com.ddabadi.service.CustomerService;
import com.ddabadi.service.HistoryCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by deddy on 6/8/16.
 */

@Service
@Transactional
public class HistoryCustomerServiceImpl implements HistoryCustomerService {

    @Autowired private HistoryCustomerRepository repository;
    @Autowired private CustomerService customerService;

    @Override
    public HistoryCustomer save(HistoryCustomer historyCustomer) {
        return repository.saveAndFlush(historyCustomer);
    }

    @Override
    public Page<HistoryCustomer> getByCustomerIdtglTransaksi(Long idCust, Date tglAwal, Date tglAkhir, int hal, int jumlah, Boolean allData) {

        Customer customer= customerService.getById(idCust);
        PageRequest pageRequest = new PageRequest(hal-1, jumlah, Sort.Direction.ASC,"id");
        if(allData.equals(Boolean.TRUE)){
            return repository.findByCustomer(customer,pageRequest);
        }else{
            return repository.findByCustomerAndTglTransaksiBetween(customer, tglAwal, tglAkhir, pageRequest);
        }

    }
}
