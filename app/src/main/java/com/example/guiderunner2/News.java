package com.example.guiderunner2;

import java.util.Date;

public class News {
    private String gamename;
    private String title;
    private String content;
    private String source;
    private Date date;

    public News(String gamename, String title, String content, String source, Date date) {
        this.gamename = gamename;
        this.title = title;
        this.content = content;
        this.source = source;
        this.date = date;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
