package com.ddabadi.dto;

/**
 * Created by deddy on 6/13/16.
 */
public class DataPembayaranSupplierDto {

    private Long idHd;
    private Long idCaraBayar;
    private String tglBayar;
    private String keterangan;

    public Long getIdHd() {
        return idHd;
    }

    public void setIdHd(Long idHd) {
        this.idHd = idHd;
    }

    public Long getIdCaraBayar() {
        return idCaraBayar;
    }

    public void setIdCaraBayar(Long idCaraBayar) {
        this.idCaraBayar = idCaraBayar;
    }

    public String getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(String tglBayar) {
        this.tglBayar = tglBayar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
