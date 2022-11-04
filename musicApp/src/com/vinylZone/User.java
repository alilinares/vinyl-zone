package com.vinylZone;

import java.io.File;

public class User {

    //fields

    int userID;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private File profilePhoto;
    private String bio; //250 bio character description limit.

    //contructors
    /**
     * Required Parameters:
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param role
	 * @param profilePhoto
	 * @param bio
	 */
    
	public User(String username, String firstName, String lastName, String email, String password, String role,
			File profilePhoto, String bio) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.profilePhoto = profilePhoto;
		this.bio = bio;
	}
	
    //methods

	protected void setUserID(int userID) {
        this.userID = userID;
    }

	protected void setUsername(String username) {
        this.username = username;
    }

	protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

	protected void setEmail(String email) {
        this.email = email;
    }

	protected void setPassword(String password) {
        this.password = password;
    }

	protected  void setRole(String role) {
        this.role = role;
    }

	protected void setBio(String bio) {
        this.bio = bio;
    }

	protected void setProfilePhoto(File profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public File getProfilePhoto() {
		return profilePhoto;
	}

	public String getBio() {
		return bio;
	}
	
	
}