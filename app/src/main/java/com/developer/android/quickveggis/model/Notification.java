package com.developer.android.quickveggis.model;

public class Notification {

    String content;
    String dateTime;

    public Notification(String content, String dateTime) {
        this.content = content;
        this.dateTime = dateTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
