package com.ddabadi.exception;

/**
 * Created by deddy on 6/10/16.
 */
public class StokTidakCukupException extends RuntimeException {

    private String pesan;

    public StokTidakCukupException(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

}
