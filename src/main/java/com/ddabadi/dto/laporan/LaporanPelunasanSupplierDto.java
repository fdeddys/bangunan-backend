package com.ddabadi.dto.laporan;

import java.util.Date;

/**
 * Created by deddy on 6/26/16.
 */
public class LaporanPelunasanSupplierDto {

    private Long id;
    private String namaSupp;
    private String noFaktur;
    private Date tglTerima;
    private String keterangan;
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
