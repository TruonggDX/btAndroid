package com.example.baitapmau.model;

public class MayTinh {
    private String maMayTinh;
    private String tenMayTinh;
    public String loaiMay;
    private String hangSX;
    private Integer soLuong;
    private Double donGia;

    public MayTinh() {
    }

    public MayTinh(String maMayTinh, String tenMayTinh, String loaiMay, String hangSX, Integer soLuong, Double donGia) {
        this.maMayTinh = maMayTinh;
        this.tenMayTinh = tenMayTinh;
        this.loaiMay = loaiMay;
        this.hangSX = hangSX;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaMayTinh() {
        return maMayTinh;
    }

    public void setMaMayTinh(String maMayTinh) {
        this.maMayTinh = maMayTinh;
    }

    public String getTenMayTinh() {
        return tenMayTinh;
    }

    public void setTenMayTinh(String tenMayTinh) {
        this.tenMayTinh = tenMayTinh;
    }

    public String getLoaiMay() {
        return loaiMay;
    }

    public void setLoaiMay(String loaiMay) {
        this.loaiMay = loaiMay;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }
}
