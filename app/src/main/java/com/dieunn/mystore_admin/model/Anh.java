package com.dieunn.mystore_admin.model;

public class Anh {
    private String image;
    private boolean isSelected;

    public Anh() {
    }

    public Anh(String image, boolean isSelected) {
        this.image = image;
        this.isSelected = isSelected;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
