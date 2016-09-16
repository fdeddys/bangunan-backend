package com.ddabadi.dto;

/**
 * Created by deddy on 6/11/16.
 */
public class ReturDtDto {

    private long idBarang;
    private Double harga;
    private Long jumlah;
    private Long idHd;

    public long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(long idBarang) {
        this.idBarang = idBarang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public Long getIdHd() {
        return idHd;
    }

    public void setIdHd(Long idHd) {
        this.idHd = idHd;
    }
}
