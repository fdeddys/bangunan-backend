package com.ddabadi.dto;

/**
 * Created by deddy on 6/3/16.
 */
public class PenerimaanDtDto {

    private long idBarang;
    private Double harga;
    private Long jumlah;
    private Long idPenerimaanHd;

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

    public Long getIdPenerimaanHd() {
        return idPenerimaanHd;
    }

    public void setIdPenerimaanHd(Long idPenerimaanHd) {
        this.idPenerimaanHd = idPenerimaanHd;
    }
}
