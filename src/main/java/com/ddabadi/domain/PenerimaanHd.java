package com.ddabadi.domain;

import com.ddabadi.enumera.StatusTerima;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 5/25/16.
 */

@Entity
@Table(name = "tb_terima_hd")
public class PenerimaanHd {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    @Column(name = "no_faktur", length = 50)
    private String noFaktur;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_terima")
    private Date tglTerima;

    @Column(name = "keterangan", length = 100)
    private String keterangan;

    @Column(name="status")
    private StatusTerima statusTerima;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", columnDefinition= "DATETIME")
    private Date lastUpdate;

    @Column(name = "user_update", length = 50)
    private String userUpdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_bayar", columnDefinition = "DATETIME")
    private Date tglBayar;

    @Column(name = "keterangan_bayar", length = 100)
    private String keteranganBayar;

    @OneToOne
    @JoinColumn(name = "cara_bayar")
    private CaraBayar caraBayar;

    @Column(name="total")
    private Double total;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public CaraBayar getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(CaraBayar caraBayar) {
        this.caraBayar = caraBayar;
    }

    public Date getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(Date tglBayar) {
        this.tglBayar = tglBayar;
    }

    public String getKeteranganBayar() {
        return keteranganBayar;
    }

    public void setKeteranganBayar(String keteranganBayar) {
        this.keteranganBayar = keteranganBayar;
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
}
