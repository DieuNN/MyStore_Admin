package com.dieunn.mystore_admin.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonHang {
    private String id;
    private String user_id;
    private String address;
    private String name;
    private String phone_number;
    private int tongTien;
    private List<DonHangChiTiet> donHangChiTietList;
    private Shipper shipper;
    private String ghiChu;
    private String trangThai;
    private String thoiGianDatHang;
    private long thoiGianGiaoHang;
    private String thong_tin_huy_don;

    public DonHang() {
    }

    public DonHang(String id, String user_id, String address, String name, String phone_number, int tongTien, List<DonHangChiTiet> donHangChiTietList, Shipper shipper, String ghiChu, String trangThai, String thoiGianDatHang, long thoiGianGiaoHang, String thong_tin_huy_don) {
        this.id = id;
        this.user_id = user_id;
        this.address = address;
        this.name = name;
        this.phone_number = phone_number;
        this.tongTien = tongTien;
        this.donHangChiTietList = donHangChiTietList;
        this.shipper = shipper;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.thoiGianDatHang = thoiGianDatHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.thong_tin_huy_don = thong_tin_huy_don;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public List<DonHangChiTiet> getDonHangChiTietList() {
        return donHangChiTietList;
    }

    public void setDonHangChiTietList(List<DonHangChiTiet> donHangChiTietList) {
        this.donHangChiTietList = donHangChiTietList;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThoiGianDatHang() {
        return thoiGianDatHang;
    }

    public void setThoiGianDatHang(String thoiGianDatHang) {
        this.thoiGianDatHang = thoiGianDatHang;
    }

    public long getThoiGianGiaoHang() {
        return thoiGianGiaoHang;
    }

    public void setThoiGianGiaoHang(long thoiGianGiaoHang) {
        this.thoiGianGiaoHang = thoiGianGiaoHang;
    }

    public String getThong_tin_huy_don() {
        return thong_tin_huy_don;
    }

    public void setThong_tin_huy_don(String thong_tin_huy_don) {
        this.thong_tin_huy_don = thong_tin_huy_don;
    }

    public Map<String, Object> LyDoHuyDon() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("thong_tin_huy_don", thong_tin_huy_don);
        map.put("trangThai",trangThai);
        return map;
    }
}
