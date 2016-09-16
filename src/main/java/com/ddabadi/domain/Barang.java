package com.ddabadi.domain;

import com.ddabadi.enumera.Status;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */

@Entity
@Table(name = "tb_barang",
       indexes = @Index(name = "ix_nama",columnList = "nama"))
public class Barang {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", length = 100)
    private String nama;

    @OneToOne
    @JoinColumn(name = "satuan")
    private Satuan satuan;

    @Column(name = "aktif")
    private Status status;

    @Column(name = "hpp")
    private Double hpp;

    @Column(name="is_jasa")
    private boolean jasa;

    public boolean isJasa() {
        return jasa;
    }

    public void setJasa(boolean jasa) {
        this.jasa = jasa;
    }

    public Double getHpp() {
        return hpp;
    }

    public void setHpp(Double hpp) {
        this.hpp = hpp;
    }


    //    @OneToMany(mappedBy = "stock")
//    private Stock stock;
//
//    public Stock getStock() {
//        return stock;
//    }
//
//    public void setStock(Stock stock) {
//        this.stock = stock;
//    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Satuan getSatuan() {
        return satuan;
    }

    public void setSatuan(Satuan satuan) {
        this.satuan = satuan;
    }
}
