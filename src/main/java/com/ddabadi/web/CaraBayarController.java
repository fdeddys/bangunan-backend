package com.ddabadi.web;

import com.ddabadi.domain.CaraBayar;
import com.ddabadi.service.CaraBayarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by deddy on 6/12/16.
 */

@RestController
@RequestMapping(value = "api/caraBayar",
                produces = "application/json")
public class CaraBayarController {

    @Autowired private CaraBayarService caraBayarService;

    @RequestMapping(value = "{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    CaraBayar getById(@PathVariable("id")Long id){
        return caraBayarService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    CaraBayar getById(@RequestBody CaraBayar caraBayar){
        return caraBayarService.save(caraBayar);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.PUT)
    CaraBayar edit(@PathVariable("id")Long id,
                   @RequestBody CaraBayar caraBayar){
        return caraBayarService.update(id, caraBayar);
    }

    @RequestMapping(value = "nama/{nama}/hal/{hal}/jumlah/{jumlah}",
                    method = RequestMethod.GET)
    Page<CaraBayar> getByNama(@PathVariable("nama")String nama,
                              @PathVariable("hal")int hal,
                              @PathVariable("jumlah")int jumlah){

        return caraBayarService.getByNama(nama.trim(), hal, jumlah);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET)
    List<CaraBayar> getAll(){

        return caraBayarService.getALl();
    }


}
