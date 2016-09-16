package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 5/25/16.
 */

@Entity
@Table(name = "tb_terima_dt")
public class PenerimaanDt {

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
    @JoinColumn(name = "penerimaan_hd")
    private PenerimaanHd penerimaanHd;

    public PenerimaanHd getPenerimaanHd() {
        return penerimaanHd;
    }

    public void setPenerimaanHd(PenerimaanHd penerimaanHd) {
        this.penerimaanHd = penerimaanHd;
    }

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
}
