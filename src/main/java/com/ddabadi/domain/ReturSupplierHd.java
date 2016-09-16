package com.ddabadi.domain;

import com.ddabadi.enumera.StatusTerima;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 6/10/16.
 */

@Entity
@Table(name = "tb_retur_supplier_hd")
public class ReturSupplierHd {


    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_retur")
    private Date tglRetur;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

    @Column(name="status")
    private StatusTerima statusTerima;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", columnDefinition= "DATETIME")
    private Date lastUpdate;

    @Column(name = "user_update", length = 50)
    private String userUpdate;

    public StatusTerima getStatusTerima() {
        return statusTerima;
    }

    public void setStatusTerima(StatusTerima statusTerima) {
        this.statusTerima = statusTerima;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
}
