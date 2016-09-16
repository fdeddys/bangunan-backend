package com.ddabadi.exception;

/**
 * Created by deddy on 6/8/16.
 */
public class SaldoCustomerTidakCukupException extends RuntimeException {
    private String pesan;

    public SaldoCustomerTidakCukupException(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }
}
