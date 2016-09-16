package com.ddabadi.dto;

/**
 * Created by deddy on 6/2/16.
 */
public class PenerimaanHdDto {

    private Long id;
    private Long idSupplier;
    private String noFaktur;
    private String tglTerima;
    private String keterangan;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(String tglTerima) {
        this.tglTerima = tglTerima;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
