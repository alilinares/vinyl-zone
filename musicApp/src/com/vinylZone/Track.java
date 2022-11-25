package com.vinylZone;

public class Track {


    //fields
    private int trackId;

    private int duration;
    private String title;
    private String lyrics;
    private String releaseDate;


    //constructors
    public Track(int trackId, int duration, String title, String lyrics, String releaseDate){
        super();
        this.trackId = trackId;
        this.duration = duration;
        this.title = title;
        this.lyrics = lyrics;
        this.releaseDate = releaseDate;
    }



    //methods


    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(String realeaseDate) {
        this.realeaseDate = realeaseDate;
    }
}