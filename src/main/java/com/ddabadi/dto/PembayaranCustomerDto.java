package com.ddabadi.dto;

import java.util.Date;

/**
 * Created by deddy on 6/25/16.
 */
public class PembayaranCustomerDto {

    private Long id;
    private Date tglJual;
    private String namaCustomer;
    private String keterangan;
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTglJual() {
        return tglJual;
    }

    public void setTglJual(Date tglJual) {
        this.tglJual = tglJual;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
