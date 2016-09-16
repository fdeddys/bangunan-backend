package com.ddabadi.dto;

/**
 * Created by deddy on 6/7/16.
 */
public class PenjualanDtDto {

    private long idBarang;
    private Double harga;
    private Long jumlah;
    private Long idPenjualanHd;

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

    public Long getIdPenjualanHd() {
        return idPenjualanHd;
    }

    public void setIdPenjualanHd(Long idPenjualanHd) {
        this.idPenjualanHd = idPenjualanHd;
    }
}
