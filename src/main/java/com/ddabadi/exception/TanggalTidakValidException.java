package com.ddabadi.exception;

/**
 * Created by deddy on 6/13/16.
 */
public class TanggalTidakValidException extends RuntimeException {

    private String pesan;

    public TanggalTidakValidException(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

}
