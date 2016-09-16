package com.ddabadi.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 5/27/16.
 */
@Entity
@Table(name = "tb_customer",
        indexes = @Index(name = "ix_nama",columnList = "nama"))
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

    @Column(name = "max_limit")
    private Double maxLimit;

    @Column(name = "sisa")
    private Double sisa;

    @Column(name = "max_limit_jasa")
    private Double maxLimitJasa;

    @Column(name = "sisa_jasa")
    private Double sisaJasa;

    @Column(name = "is_tutup")
    private Boolean isTutup;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_open")
    private Date tglOpen;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_close")
    private Date tglClose;

    public Customer() {
        this.isTutup=false;
        this.maxLimit=0D;
        this.sisa=0D;
        this.tglOpen = new Date();
        this.tglClose=null;
    }

    public Double getMaxLimitJasa() {
        return maxLimitJasa;
    }

    public void setMaxLimitJasa(Double maxLimitJasa) {
        this.maxLimitJasa = maxLimitJasa;
    }

    public Double getSisaJasa() {
        return sisaJasa;
    }

    public void setSisaJasa(Double sisaJasa) {
        this.sisaJasa = sisaJasa;
    }

    public Date getTglOpen() {
        return tglOpen;
    }

    public void setTglOpen(Date tglOpen) {
        this.tglOpen = tglOpen;
    }

    public Date getTglClose() {
        return tglClose;
    }

    public void setTglClose(Date tglClose) {
        this.tglClose = tglClose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Double maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Double getSisa() {
        return sisa;
    }

    public void setSisa(Double sisa) {
        this.sisa = sisa;
    }

    public Boolean getIsTutup() {
        return isTutup;
    }

    public void setIsTutup(Boolean isTutup) {
        this.isTutup = isTutup;
    }
}
