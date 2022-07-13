package com.example.itest_pro.Model;

public class Hang {
    private Integer id;
    private String mota;

    public Hang() {
    }

    public Hang(Integer id, String mota) {
        this.id = id;
        this.mota = mota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
