package com.moonayoung.restful;

import com.google.gson.annotations.SerializedName;

public class Diary {
    @SerializedName("date")
    String date;
    @SerializedName("writer")
    String writer;
    @SerializedName("title")
    String title;
    @SerializedName("content")
    String content;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
