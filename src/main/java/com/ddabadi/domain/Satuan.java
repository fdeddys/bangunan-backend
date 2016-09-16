package com.ddabadi.domain;

import javax.persistence.*;

/**
 * Created by deddy on 5/24/16.
 */

@Entity
@Table(name = "tb_satuan")
public class Satuan {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nama", length = 100)
    private String nama;

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
