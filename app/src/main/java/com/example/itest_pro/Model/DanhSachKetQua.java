package com.example.itest_pro.Model;

public class DanhSachKetQua {
    private String monthi;
    private String thoigianthi;
    private String diemthi;
    private String bode;

    public DanhSachKetQua() {
        super();
    }

    public DanhSachKetQua(String monthi, String thoigianthi, String diemthi, String bode) {
        super();
        this.monthi = monthi;
        this.thoigianthi = thoigianthi;
        this.diemthi = diemthi;
        this.bode = bode;
    }

    public String getMonthi() {
        return monthi;
    }

    public void setMonthi(String monthi) {
        this.monthi = monthi;
    }

    public String getThoigianthi() {
        return thoigianthi;
    }

    public void setThoigianthi(String thoigianthi) {
        this.thoigianthi = thoigianthi;
    }

    public String getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(String diemthi) {
        this.diemthi = diemthi;
    }

    public String getBode() {
        return bode;
    }

    public void setBode(String bode) {
        this.bode = bode;
    }

}
