package com.vinylZone;

public class PlaylistBuilder {
    private int playlistId;
    private String title;
    private int duration;
    private String description;

    public PlaylistBuilder setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
        return this;
    }

    public PlaylistBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PlaylistBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public PlaylistBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Playlist createPlaylist() {
        return new Playlist(playlistId, title, duration, description);
    }
}