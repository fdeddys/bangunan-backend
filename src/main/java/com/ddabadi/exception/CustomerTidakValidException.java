package com.ddabadi.exception;

/**
 * Created by deddy on 9/16/16.
 */
public class CustomerTidakValidException extends RuntimeException {

    private String pesan;

    public CustomerTidakValidException(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

}
