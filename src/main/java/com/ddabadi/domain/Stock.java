package com.ddabadi.domain;

import com.ddabadi.domain.Barang;
import com.ddabadi.domain.Gudang;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */

@Entity
@Table(name = "tb_stock")
public class Stock {

    public Stock() {
        this.barang=null;
        this.gudang=null;
        this.qty=0L;
        this.lastUpdate= new Date();
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barang")
    private Barang barang;

    @ManyToOne
    @JoinColumn(name = "gudang")
    private Gudang gudang;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "cur_hpp")
    private Double curHpp;

    public Double getCurHpp() {
        return curHpp;
    }

    public void setCurHpp(Double curHpp) {
        this.curHpp = curHpp;
    }

    @Temporal(TemporalType.DATE)
    private Date lastUpdate;

    @Column(name = "keterangan")
    private String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
