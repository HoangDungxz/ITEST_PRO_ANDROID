package com.example.itest_pro.Model;

import java.util.List;

public class CauHoiData {
    private Integer idbode;
    private Integer cuocthiid;
    private List<Cauhoi> listCauHoi;
    private String thoigianlambai;
    private String tenbode;
    private String tenmon;

    public CauHoiData() {
    }

    public CauHoiData(Integer idbode, Integer cuocthiid, List<Cauhoi> listCauHoi, String thoigianlambai, String tenbode,
                      String tenmon) {
        super();
        this.idbode = idbode;
        this.cuocthiid = cuocthiid;
        this.listCauHoi = listCauHoi;
        this.thoigianlambai = thoigianlambai;
        this.tenbode = tenbode;
        this.tenmon = tenmon;
    }

    public Integer getIdbode() {
        return idbode;
    }

    public void setIdbode(Integer idbode) {
        this.idbode = idbode;
    }

    public Integer getCuocthiid() {
        return cuocthiid;
    }

    public void setCuocthiid(Integer cuocthiid) {
        this.cuocthiid = cuocthiid;
    }

    public List<Cauhoi> getListCauHoi() {
        return listCauHoi;
    }

    public void setListCauHoi(List<Cauhoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
    }

    public String getThoigianlambai() {
        return thoigianlambai;
    }

    public void setThoigianlambai(String thoigianlambai) {
        this.thoigianlambai = thoigianlambai;
    }

    public String getTenbode() {
        return tenbode;
    }

    public void setTenbode(String tenbode) {
        this.tenbode = tenbode;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

}