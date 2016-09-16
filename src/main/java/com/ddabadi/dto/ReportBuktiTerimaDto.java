package com.ddabadi.dto;

import java.util.Date;

/**
 * Created by deddy on 6/5/16.
 */
public class ReportBuktiTerimaDto {

    private Long idHd;
    private String supplier;
    private String keterangan;
    private String noFaktur;
    private Date tglTerima;
    private String status;

    private String namaBarang;
    private String satuan;
    private Long jumlah;
    private Double harga;
    private Double total;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdHd() {
        return idHd;
    }

    public void setIdHd(Long idHd) {
        this.idHd = idHd;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public Date getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(Date tglTerima) {
        this.tglTerima = tglTerima;
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
