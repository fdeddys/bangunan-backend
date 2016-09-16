package com.ddabadi.dto;

import java.util.Date;

/**
 * Created by deddy on 6/12/16.
 */
public class PembayaranSupplierDto {

    private Long id;
    private Date tglTerima;
    private String namaSupplier;
    private String noFaktur;
    private String keterangan;
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(Date tglTerima) {
        this.tglTerima = tglTerima;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
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
