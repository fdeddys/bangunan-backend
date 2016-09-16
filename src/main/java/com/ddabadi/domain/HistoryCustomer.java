package com.ddabadi.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 6/8/16.
 */
@Entity
@Table(name = "history_customer")
public class HistoryCustomer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

    @Column(name = "masuk")
    private Double masuk;

    @Column(name = "keluar")
    private Double keluar;

    @Column(name = "akhir")
    private Double akhir;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_transaksi")
    private Date tglTransaksi;

    @Column(name = "id_transaksi")
    private String idTransaksi;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getMasuk() {
        return masuk;
    }

    public void setMasuk(Double masuk) {
        this.masuk = masuk;
    }

    public Double getKeluar() {
        return keluar;
    }

    public void setKeluar(Double keluar) {
        this.keluar = keluar;
    }

    public Double getAkhir() {
        return akhir;
    }

    public void setAkhir(Double akhir) {
        this.akhir = akhir;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }
}
