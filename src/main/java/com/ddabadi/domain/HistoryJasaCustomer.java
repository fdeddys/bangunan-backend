package com.ddabadi.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 9/4/16.
 */

@Entity
@Table(name = "history_jasa_cust")
public class HistoryJasaCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

    @Column(name = "jumlah")
    private Double jumlah;

    @Column(name = "sisa")
    private Double sisa;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_transaksi")
    private Date tglTransaksi;

    @Column(name = "id_transaksi")
    private Long idTransaksi;

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

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public Double getSisa() {
        return sisa;
    }

    public void setSisa(Double sisa) {
        this.sisa = sisa;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
}
