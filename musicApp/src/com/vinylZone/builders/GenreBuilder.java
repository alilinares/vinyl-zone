package com.vinylZone.builders;

import com.vinylZone.beans.Genre;

public class GenreBuilder {
    private int genreId;
    private String name;
    private String description;

    public GenreBuilder setGenreId(int genreId) {
        this.genreId = genreId;
        return this;
    }

    public GenreBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GenreBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Genre createGenre() {
        return new Genre(genreId, name, description);
    }
}