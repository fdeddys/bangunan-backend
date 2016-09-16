package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 8/30/16.
 */

@Entity
@Table(name = "tb_developer")
public class Mandor {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "keterangan", length = 200)
    private String keterangan;

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
