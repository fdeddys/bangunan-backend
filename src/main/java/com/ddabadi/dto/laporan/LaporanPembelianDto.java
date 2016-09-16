package com.ddabadi.dto.laporan;

import com.ddabadi.enumera.StatusTerima;

import java.util.Date;

/**
 * Created by deddy on 6/25/16.
 */
public class LaporanPembelianDto {

    private Long id;
    private String namaSupp;
    private String noFaktur;
    private Date tglTerima;
    private String keterangan;
    private StatusTerima statusTerima;
    private Double total;
    private Date tglBayar;
    private String caraBayar;
    private String ketBayar;

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public StatusTerima getStatusTerima() {
        return statusTerima;
    }

    public void setStatusTerima(StatusTerima statusTerima) {
        this.statusTerima = statusTerima;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
