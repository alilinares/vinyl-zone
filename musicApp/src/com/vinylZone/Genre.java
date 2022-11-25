package com.vinylZone;

public class Genre {

    //fields

    private int genreId;

    private String name;
    private String description;

    //constructors
    public Genre(int genreId, String name, String description){

        this.genreId = genreId;
        this.name = name;
        this.description = description;
    }

    //methods


    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}