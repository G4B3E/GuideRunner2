package com.example.guiderunner2;

import java.util.Date;
import java.util.List;

public class News {

    private int id;
    private String gamename;
    private String title;
    private String content;
    private String source;
    private String date;


    public News(int id,String gamename, String title, String content, String source, String date) {
        this.id = id;
        this.gamename = gamename;
        this.title = title;
        this.content = content;
        this.source = source;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
class NewsListHelper {
    private List<News> news;

    public List<News> getNews() {
        return news;
    }
}
