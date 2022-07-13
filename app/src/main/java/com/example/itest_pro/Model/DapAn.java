package com.example.itest_pro.Model;

public class DapAn {
    private Integer id;
    private String noidung;

    private boolean checked;

    public DapAn() {
    }

    public DapAn(Integer id, String noidung) {
        this.id = id;
        this.noidung = noidung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
