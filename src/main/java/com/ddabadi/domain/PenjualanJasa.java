package com.ddabadi.domain;

import com.ddabadi.enumera.StatusTerima;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 8/30/16.
 */
@Entity
@Table(name = "penjualan_jasa")
public class PenjualanJasa {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_transaksi")
    private Date tglTransaksi;

    @Column(name = "keterangan", length =200 )
    private String keterangan;

    @Column(name = "Status")
    private StatusTerima statusJual;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", columnDefinition= "DATETIME")
    private Date lastUpdate;

    @Column(name = "user_update", length = 50)
    private String userUpdate;

    @Column(name="jumlah")
    private Double jumlah;

    @OneToOne
    @JoinColumn(name="mandor")
    private Mandor mandor;

    public Mandor getMandor() {
        return mandor;
    }

    public void setMandor(Mandor mandor) {
        this.mandor = mandor;
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

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
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

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
