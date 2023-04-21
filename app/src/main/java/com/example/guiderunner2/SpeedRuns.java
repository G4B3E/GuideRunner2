package com.example.guiderunner2;

import java.util.List;

public class SpeedRuns {
    private int id;
    private String username;
    private String gamename;
    private String time;
    private String platform;
    private String difficulty;
    private String youtubelink;

    public SpeedRuns(int id, String username, String gamename, String time, String platform, String difficulty, String youtubelink) {
        this.id = id;
        this.username = username;
        this.gamename = gamename;
        this.time = time;
        this.platform = platform;
        this.difficulty = difficulty;
        this.youtubelink = youtubelink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
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

class SpeedRunsListHelper {
    private List<SpeedRuns> speedruns;

    public List<SpeedRuns> getSpeedruns() {
        return speedruns;
    }
}
