package com.vinylZone;

public class TrackBuilder {
    private int trackId;
    private int duration;
    private String title;
    private String lyrics;
    private String releaseDate;

    public TrackBuilder setTrackId(int trackId) {
        this.trackId = trackId;
        return this;
    }

    public TrackBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public TrackBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TrackBuilder setLyrics(String lyrics) {
        this.lyrics = lyrics;
        return this;
    }

    public TrackBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Track createTrack() {
        return new Track(trackId, duration, title, lyrics, releaseDate);
    }
}