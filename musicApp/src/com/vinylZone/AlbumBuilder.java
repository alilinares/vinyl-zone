package com.vinylZone;

public class AlbumBuilder {
    private int albumId;
    private String title;
    private int duration;
    private String releaseDate;
    private String description;

    public AlbumBuilder setAlbumId(int albumId) {
        this.albumId = albumId;
        return this;
    }

    public AlbumBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlbumBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AlbumBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public AlbumBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Album createAlbum() {
        return new Album(albumId, title, duration, releaseDate, description);
    }
}