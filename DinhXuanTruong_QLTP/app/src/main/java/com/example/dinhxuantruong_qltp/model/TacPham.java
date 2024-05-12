package com.example.dinhxuantruong_qltp.model;

public class TacPham {
    private String maTacPham;
    private String tenTacPham;
    private String nhaXuatBan;
    private Integer soXuatBan;
    private Integer soLuong;
    private Double donGia;

    public TacPham() {
    }

    public TacPham(String maTacPham, String tenTacPham, String nhaXuatBan, Integer soXuatBan, Integer soLuong, Double donGia) {
        this.maTacPham = maTacPham;
        this.tenTacPham = tenTacPham;
        this.nhaXuatBan = nhaXuatBan;
        this.soXuatBan = soXuatBan;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaTacPham() {
        return maTacPham;
    }

    public void setMaTacPham(String maTacPham) {
        this.maTacPham = maTacPham;
    }

    public String getTenTacPham() {
        return tenTacPham;
    }

    public void setTenTacPham(String tenTacPham) {
        this.tenTacPham = tenTacPham;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public Integer getSoXuatBan() {
        return soXuatBan;
    }

    public void setSoXuatBan(Integer soXuatBan) {
        this.soXuatBan = soXuatBan;
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
