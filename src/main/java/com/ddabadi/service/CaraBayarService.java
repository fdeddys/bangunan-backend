package com.ddabadi.service;

import com.ddabadi.domain.CaraBayar;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by deddy on 6/12/16.
 */
public interface CaraBayarService {

    CaraBayar getById(Long id);
    CaraBayar save(CaraBayar caraBayar);
    CaraBayar update(Long idUpdate, CaraBayar caraBayar);
    List<CaraBayar> getALl();
    Page<CaraBayar> getByNama(String nama, int hal, int jumlah);
}
