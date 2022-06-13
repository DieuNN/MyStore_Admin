package com.dieunn.mystore_admin.model;

public class DonHangChiTiet {
    private SanPham sanPham;
    private int soLuong;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
