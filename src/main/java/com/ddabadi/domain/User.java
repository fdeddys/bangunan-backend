package com.ddabadi.domain;

import com.ddabadi.enumera.Status;

import javax.persistence.*;

/**
 * Created by deddy on 5/25/16.
 */

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "nama", length = 100)
    private String nama;

    @Column(name = "password",length = 200)
    private String password;

    @Column(name = "status")
    private Status status;

    @OneToOne
    @JoinColumn(name = "gudang")
    private Gudang gudang;

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
