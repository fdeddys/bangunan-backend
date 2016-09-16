package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 6/7/16.
 */
@Entity
@Table(name = "tb_jual_dt")
public class PenjualanDt {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "barang")
    private Barang barang;

    @Column(name = "harga")
    private Double harga;

    @Column(name = "jumlah")
    private Long jumlah;

    @ManyToOne
    @JoinColumn(name = "penjualan_hd")
    private PenjualanHd penjualanHd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public PenjualanHd getPenjualanHd() {
        return penjualanHd;
    }

    public void setPenjualanHd(PenjualanHd penjualanHd) {
        this.penjualanHd = penjualanHd;
    }
}
