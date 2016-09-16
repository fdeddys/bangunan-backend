package com.ddabadi.exception;

import com.ddabadi.service.SupplierService;
import org.apache.log4j.Logger;

/**
 * Created by deddy on 6/4/16.
 */
public class SupplierTidakValidException extends RuntimeException {


    private String pesan;

    public SupplierTidakValidException(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }
}
