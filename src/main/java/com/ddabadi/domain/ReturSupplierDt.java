package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 6/10/16.
 */
@Entity
@Table(name = "tb_retur_supplier_dt")
public class ReturSupplierDt {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "barang")
    private Barang barang;

    @Column(name = "harga")
    private Double harga;

    @Column(name = "jumlah")
    private Long jumlah;

    @ManyToOne
    @JoinColumn(name = "returHd")
    private ReturSupplierHd returSupplierHd;

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

    public ReturSupplierHd getReturSupplierHd() {
        return returSupplierHd;
    }

    public void setReturSupplierHd(ReturSupplierHd returSupplierHd) {
        this.returSupplierHd = returSupplierHd;
    }
}
