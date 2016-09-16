package com.ddabadi.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 * Created by deddy on 6/7/16.
 */
public class PenjualanHdDto {

    private Long id;
    private Long customerId;
    private String tglJual;
    private String keterangan;
    private String userUpdate;
    private Boolean isTransBarang;

    public Boolean getIsTransBarang() {
        return isTransBarang;
    }

    public void setIsTransBarang(Boolean isTransBarang) {
        this.isTransBarang = isTransBarang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTglJual() {
        return tglJual;
    }

    public void setTglJual(String tglJual) {
        this.tglJual = tglJual;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }
}
