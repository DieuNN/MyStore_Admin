package com.dieunn.mystore_admin.model;

public class GioHang {
     private String ma_sp;
     private int soLuong;

     public GioHang() {
     }

     public GioHang(String ma_sp, int soLuong) {
          this.ma_sp = ma_sp;
          this.soLuong = soLuong;
     }

     public String getMa_sp() {
          return ma_sp;
     }

     public void setMa_sp(String ma_sp) {
          this.ma_sp = ma_sp;
     }

     public int getSoLuong() {
          return soLuong;
     }

     public void setSoLuong(int soLuong) {
          this.soLuong = soLuong;
     }

     @Override
     public String toString() {
          return "GioHang{" +
                  "ma_sp='" + ma_sp + '\'' +
                  ", soLuong=" + soLuong +
                  '}';
     }
}
