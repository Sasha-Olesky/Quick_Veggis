package com.developer.android.quickveggis.model;

public class Category {
    int icon;
    int title;

    public Category(int icon, int title) {
        this.title = title;
        this.icon = icon;
    }

    public int getTitle() {
        return this.title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
