package com.ddabadi.exception;

/**
 * Created by deddy on 9/16/16.
 */
public class MandorTidakValid extends RuntimeException {
    private String pesan;

    public MandorTidakValid(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
