package com.ddabadi.dto;

/**
 * Created by deddy on 9/16/16.
 */
public class PenjualanJasaDto {

    private Long id;
    private Long idCust;
    private String tglTransaksi;
    private String keterangan;
    private String userUpdate;
    private Double jumlah;
    private Long idMandor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCust() {
        return idCust;
    }

    public void setIdCust(Long idCust) {
        this.idCust = idCust;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
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

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public Long getIdMandor() {
        return idMandor;
    }

    public void setIdMandor(Long idMandor) {
        this.idMandor = idMandor;
    }
}
