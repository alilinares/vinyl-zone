package com.vinylZone.beans;

public class Playlist {
    //fields
    private int playlistId;

    private String title;
    private int duration;
    private String description;

    //constructors
    public Playlist(int playlistId, String title, int duration, String description){

        this.playlistId = playlistId;
        this.title = title;
        this.duration = duration;
        this.description = description;
    }


    //methods

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}