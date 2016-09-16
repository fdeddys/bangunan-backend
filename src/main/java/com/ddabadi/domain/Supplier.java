package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 5/23/16.
 */

@Entity
@Table(name = "tb_supplier",
       indexes = @Index(name = "nama", columnList = "nama") )

public class Supplier {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "alamat", length = 250)
    private  String  alamat;

    @Column(name = "kota", length = 50)
    private String kota;

    @Column(name="Telp", length = 50)
    private String telp;

    @Column(name = "kontak", length = 100)
    private String kontak;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
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
}
