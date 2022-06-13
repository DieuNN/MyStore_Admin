package com.dieunn.mystore_admin.model;


import java.util.List;

public class SanPham  {
    private String id;
    private String name;
    private String loai_sp;
    private String moTa;
    private int gia_ban;
    private float khuyen_mai;
    private List<Color> mauSac;
    private List<Size> size;
    private List<Anh> anh;

    public SanPham() {
    }

    public SanPham(String id, String name, String loai_sp, String moTa, int gia_ban, float khuyen_mai, List<Color> mauSac, List<Size> size, List<Anh> anh) {
        this.id = id;
        this.name = name;
        this.loai_sp = loai_sp;
        this.moTa = moTa;
        this.gia_ban = gia_ban;
        this.khuyen_mai = khuyen_mai;
        this.mauSac = mauSac;
        this.size = size;
        this.anh = anh;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai_sp() {
        return loai_sp;
    }

    public void setLoai_sp(String loai_sp) {
        this.loai_sp = loai_sp;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(int gia_ban) {
        this.gia_ban = gia_ban;
    }

    public float getKhuyen_mai() {
        return khuyen_mai;
    }

    public void setKhuyen_mai(float khuyen_mai) {
        this.khuyen_mai = khuyen_mai;
    }

    public List<Color> getMauSac() {
        return mauSac;
    }

    public void setMauSac(List<Color> mauSac) {
        this.mauSac = mauSac;
    }

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }

    public List<Anh> getAnh() {
        return anh;
    }

    public void setAnh(List<Anh> anh) {
        this.anh = anh;
    }


}
