package com.example.itest_pro.Model;

import java.util.List;

public class Cauhoi {
    private Integer id;
    private String monid;
    private String hangid;
    private String noidung;
    private Integer sodapandung;
    private List<DapAn> cacdapan;

    public Cauhoi() {
    }

    public Cauhoi(Integer id, String monid, String hangid, String noidung, Integer sodapandung, List<DapAn> cacdapan) {
        super();
        this.id = id;
        this.monid = monid;
        this.hangid = hangid;
        this.noidung = noidung;
        this.sodapandung = sodapandung;
        this.cacdapan = cacdapan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonid() {
        return monid;
    }

    public void setMonid(String monid) {
        this.monid = monid;
    }

    public String getHangid() {
        return hangid;
    }

    public void setHangid(String hangid) {
        this.hangid = hangid;
    }

    public Integer getSodapandung() {
        return sodapandung;
    }

    public void setSodapandung(Integer sodapandung) {
        this.sodapandung = sodapandung;
    }

    public List<DapAn> getCacdapan() {
        return cacdapan;
    }

    public void setCacdapan(List<DapAn> cacdapan) {
        this.cacdapan = cacdapan;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
