package com.dieunn.mystore_admin.model;

public class ThongBao {
    private int image;
    private String title;
    private String nd;
    private String date;

    public ThongBao() {
    }

    public ThongBao(int image, String title, String nd, String date) {
        this.image = image;
        this.title = title;
        this.nd = nd;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
