package com.ddabadi.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deddy on 5/24/16.
 */

@Entity
@Table(name = "tb_gudang",
       indexes = @Index(name = "ix_nama",columnList = "nama"))
public class Gudang {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nama")
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
