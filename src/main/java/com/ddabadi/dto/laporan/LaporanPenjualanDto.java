package com.ddabadi.dto.laporan;

import com.ddabadi.enumera.StatusTerima;

import java.util.Date;

/**
 * Created by deddy on 6/26/16.
 */
public class LaporanPenjualanDto {
    private Long id;
    private String namaCustomer;
    private Date tglJual;
    private String keterangan;
    private StatusTerima statusJual;
    private Double total;
    private Date tglBayar;
    private String caraBayar;
    private String ketBayar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public Date getTglJual() {
        return tglJual;
    }

    public void setTglJual(Date tglJual) {
        this.tglJual = tglJual;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public StatusTerima getStatusJual() {
        return statusJual;
    }

    public void setStatusJual(StatusTerima statusJual) {
        this.statusJual = statusJual;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public String getKetBayar() {
        return ketBayar;
    }

    public void setKetBayar(String ketBayar) {
        this.ketBayar = ketBayar;
    }
}
