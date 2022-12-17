package com.vinylZone.beans;

public class Album {

    //fields

    private int albumId;

    private String title;
    private int duration;
    private String releaseDate;
    private String description;

    //constructors

    public Album(int albumId, String title, int duration, String releaseDate, String description){


        this.albumId = albumId;
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    //methods

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}