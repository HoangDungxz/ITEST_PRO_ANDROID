package com.example.itest_pro.Model.guiketqua;

import java.util.List;

public class CauHoiDuocLuaChon {
    private Integer cauhoiid;
    List<DapAnDuocLuaChon> cacdapanduocluachon;

    public CauHoiDuocLuaChon() {
        super();
    }

    public CauHoiDuocLuaChon(Integer cauhoiid, List<DapAnDuocLuaChon> cacdapanduocluachon) {
        super();
        this.cauhoiid = cauhoiid;
        this.cacdapanduocluachon = cacdapanduocluachon;
    }

    public Integer getCauhoiid() {
        return cauhoiid;
    }

    public void setCauhoiid(Integer cauhoiid) {
        this.cauhoiid = cauhoiid;
    }

    public List<DapAnDuocLuaChon> getCacdapanduocluachon() {
        return cacdapanduocluachon;
    }

    public void setCacdapanduocluachon(List<DapAnDuocLuaChon> cacdapanduocluachon) {
        this.cacdapanduocluachon = cacdapanduocluachon;
    }

}
