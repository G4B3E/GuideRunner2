package com.example.guiderunner2;

public class Records {
    private String game;
    private String time;
    private String platform;
    private String difficulty;
    private String youtubelink;

    public Records(String game, String time, String platform, String difficulty, String youtubelink) {
        this.game = game;
        this.time = time;
        this.platform = platform;
        this.difficulty = difficulty;
        this.youtubelink = youtubelink;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getYoutubelink() {
        return youtubelink;
    }

    public void setYoutubelink(String youtubelink) {
        this.youtubelink = youtubelink;
    }
}
