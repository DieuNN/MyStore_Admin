package com.dieunn.mystore_admin.model;

public enum TrangThai {
    MOI_DAT("Mới đặt"), DANG_GIAO_HANG("Đang giao hàng"), HOAN_THANH("Hoàn thành"), HUY_DON("Hủy đơn");

    private String trangThai;

    TrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
