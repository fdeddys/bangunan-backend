package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 6/12/16.
 */
@Entity
@Table(name = "tb_cara_bayar")
public class CaraBayar {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name = "nama", length = 50)
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
