package com.ddabadi.domain;

import com.ddabadi.enumera.StatusTerima;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 6/7/16.
 */

@Entity
@Table(name = "tb_jual_hd")
public class PenjualanHd {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name="Tgl_jual")
    private Date tglJual;

    @Column(name = "keterangan", length =200 )
    private String keterangan;

    @Column(name = "Status")
    private StatusTerima statusJual;

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

    @Column(name = "is_Transaksi_Barang")
    private Boolean isTransBarang;

    public Boolean getIsTransBarang() {
        return isTransBarang;
    }

    public void setIsTransBarang(Boolean isTransBarang) {
        this.isTransBarang = isTransBarang;
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

    public String getKeteranganBayar() {
        return keteranganBayar;
    }

    public void setKeteranganBayar(String keteranganBayar) {
        this.keteranganBayar = keteranganBayar;
    }

    public CaraBayar getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(CaraBayar caraBayar) {
        this.caraBayar = caraBayar;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getTglJual() {
        return tglJual;
    }

    public void setTglJual(Date tglJual) {
        this.tglJual = tglJual;
    }
}
