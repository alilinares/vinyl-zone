package com.vinylZone.builders;

import com.vinylZone.beans.Musician;

public class MusicianBuilder {
    private int musicianId;
    private String name;

    public MusicianBuilder setMusicianId(int musicianId) {
        this.musicianId = musicianId;
        return this;
    }

    public MusicianBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Musician createMusician() {
        return new Musician(musicianId, name);
    }
}