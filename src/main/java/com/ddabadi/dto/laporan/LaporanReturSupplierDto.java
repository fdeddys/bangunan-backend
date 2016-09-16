package com.ddabadi.dto.laporan;

import com.ddabadi.enumera.StatusTerima;

import java.util.Date;

/**
 * Created by deddy on 6/26/16.
 */
public class LaporanReturSupplierDto {

    private Long id;
    private String namaSupp;
    private Date tglRetur;
    private String keterangan;
    private Double total;
    private StatusTerima statusRetur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaSupp() {
        return namaSupp;
    }

    public void setNamaSupp(String namaSupp) {
        this.namaSupp = namaSupp;
    }

    public Date getTglRetur() {
        return tglRetur;
    }

    public void setTglRetur(Date tglRetur) {
        this.tglRetur = tglRetur;
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

    public StatusTerima getStatusRetur() {
        return statusRetur;
    }

    public void setStatusRetur(StatusTerima statusRetur) {
        this.statusRetur = statusRetur;
    }
}
