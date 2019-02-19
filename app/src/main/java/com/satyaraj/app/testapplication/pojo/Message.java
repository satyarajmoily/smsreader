package com.satyaraj.app.testapplication.pojo;

public class Message {
    private String body;
    private long time;

    private String title;
    private int type;

    public Message(String body, long time, String title, int type) {
        this.body = body;
        this.time = time;
        this.title = title;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
