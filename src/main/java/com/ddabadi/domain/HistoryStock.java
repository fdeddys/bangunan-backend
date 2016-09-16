package com.ddabadi.domain;

import com.ddabadi.enumera.JenisTransaksiHistoryStock;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deddy on 5/25/16.
 */

@Entity
@Table(name = "tb_history_stock")
public class HistoryStock {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "barang")
    private Barang barang;

    @Column(name = "masuk")
    private Long masuk;

    @Column(name = "keluar")
    private Long keluar;

    @Column(name = "akhir")
    private Long akhir;

    @Column(name = "current_hpp")
    private Double currentHpp;

    @Column(name = "harga_transaksi")
    private Double hargaTransaksi;

    @Column(name = "keterangan", length = 150)
    private String keterangan;

    @Column(name = "no_bukti", length = 30)
    private String noBukti;

    @Temporal(TemporalType.DATE)
    @Column(name = "tgl_transaksi")
    private Date tglTransaksi;

    @OneToOne
    @JoinColumn(name = "gudang")
    private Gudang gudang;

    @Column(name = "jenisTransaksi")
    private JenisTransaksiHistoryStock jenisTransaksiHistoryStock;

    public JenisTransaksiHistoryStock getJenisTransaksiHistoryStock() {
        return jenisTransaksiHistoryStock;
    }

    public void setJenisTransaksiHistoryStock(JenisTransaksiHistoryStock jenisTransaksiHistoryStock) {
        this.jenisTransaksiHistoryStock = jenisTransaksiHistoryStock;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getNoBukti() {
        return noBukti;
    }

    public void setNoBukti(String noBukti) {
        this.noBukti = noBukti;
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

    public Long getMasuk() {
        return masuk;
    }

    public void setMasuk(Long masuk) {
        this.masuk = masuk;
    }

    public Long getKeluar() {
        return keluar;
    }

    public void setKeluar(Long keluar) {
        this.keluar = keluar;
    }

    public Long getAkhir() {
        return akhir;
    }

    public void setAkhir(Long akhir) {
        this.akhir = akhir;
    }

    public Double getCurrentHpp() {
        return currentHpp;
    }

    public void setCurrentHpp(Double currentHpp) {
        this.currentHpp = currentHpp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Double getHargaTransaksi() {
        return hargaTransaksi;
    }

    public void setHargaTransaksi(Double hargaTransaksi) {
        this.hargaTransaksi = hargaTransaksi;
    }
}
