package com.dieunn.mystore_admin.model;

public class Color {
    private String color;
    private boolean isSelected;

    public Color() {
    }

    public Color(String color, boolean isSelected) {
        this.color = color;
        this.isSelected = isSelected;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
