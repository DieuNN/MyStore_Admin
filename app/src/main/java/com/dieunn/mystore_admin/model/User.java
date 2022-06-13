package com.dieunn.mystore_admin.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String uId;
    private String email;
    private String password;
    private String name;
    private String phone_number;
    private String address;
    private List<GioHang> gioHangList;

    public User() {
    }

    public User(String uId, String email, String password, String name, String phone_number, String address, List<GioHang> gioHangList) {
        this.uId = uId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.gioHangList = gioHangList;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<GioHang> getGioHangList() {
        return gioHangList;
    }

    public void setGioHangList(List<GioHang> gioHangList) {
        this.gioHangList = gioHangList;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> toMapCart() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("gioHangList", gioHangList);
        return map;
    }
    public Map<String, Object> toUpdateThongTinUser(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("address",address);
        map.put("phone_number",phone_number);
        return map;
    }
}
