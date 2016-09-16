package com.ddabadi.dto;

/**
 * Created by deddy on 6/10/16.
 */
public class ReturHdDto {

    private Long id;
    private Long idSupplier;
    private String tglRetur;
    private String keterangan;
    private String userUpdate;

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

    public String getTglRetur() {
        return tglRetur;
    }

    public void setTglRetur(String tglRetur) {
        this.tglRetur = tglRetur;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }
}
