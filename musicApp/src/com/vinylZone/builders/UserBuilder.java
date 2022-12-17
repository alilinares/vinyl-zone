package com.vinylZone.builders;

import com.vinylZone.beans.User;

import java.io.InputStream;

public class UserBuilder {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private InputStream profilePhoto;
    private String bio;

    public UserBuilder setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder setProfilePhoto(InputStream profilePhoto) {
        this.profilePhoto = profilePhoto;
        return this;
    }

    public UserBuilder setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public User createUser() {
        return new User(userId, username, firstName, lastName, email, password, role, profilePhoto, bio);
    }
}