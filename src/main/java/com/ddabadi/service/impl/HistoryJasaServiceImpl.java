package com.ddabadi.service.impl;

import com.ddabadi.domain.HistoryJasaCustomer;
import com.ddabadi.domain.repository.HistoryJasaRepository;
import com.ddabadi.service.HistoryJasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deddy on 9/4/16.
 */

@Service
public class HistoryJasaServiceImpl implements HistoryJasaService {

    @Autowired private HistoryJasaRepository repository;

    @Override
    public HistoryJasaCustomer save(HistoryJasaCustomer historyJasaCustomer) {
        return repository.saveAndFlush(historyJasaCustomer);
    }
}
