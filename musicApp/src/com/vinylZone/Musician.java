package com.vinylZone;

public class Musician {

    //fields

    private int musicianId;

    private String name;


    //constructors
    public Musician(int musicianId, String name){

        this.musicianId = musicianId;
        this.name = name;
    }



    //methods


    public int getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(int musicianId) {
        this.musicianId = musicianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}