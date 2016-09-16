package com.ddabadi.dto;

import java.util.Date;

/**
 * Created by deddy on 6/10/16.
 */

public class ReportNotaPenjualanDto {

    private Long idHd;
    private String customer;
    private String keterangan;
    private Date tglJual;
    private String status;
    private String user;

    private String namaBarang;
    private String satuan;
    private Long jumlah;
    private Double harga;
    private Double total;

    public Long getIdHd() {
        return idHd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setIdHd(Long idHd) {
        this.idHd = idHd;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTglJual() {
        return tglJual;
    }

    public void setTglJual(Date tglJual) {
        this.tglJual = tglJual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
