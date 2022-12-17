package com.vinylZone.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class User {

//fields

    private int userId;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private InputStream profilePhoto;
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

	public User(int userId, String username, String firstName, String lastName, String email, String password, String role, InputStream profilePhoto, String bio) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.profilePhoto = profilePhoto;
		this.bio = bio;
	}
    
   public User(String username
    			, String firstName
    			, String lastName
    			, String email
    			, String password) {
		super();
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		// defaults
		this.role = "subscriber";
		this.bio = "...";
	}
    
    public User(int userId
    			, String username
    			, String firstName
    			, String lastName
    			, String email
    			, String password) {
		super();
		
		this.userId=userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		// defaults
		this.role = "subscriber";	
	}
    
	
	public User(int userId
				, String username
				, String firstName, String lastName
				, String email
				, String password
				, String bio
				, InputStream profilePhoto) {
		super();
		
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.profilePhoto = profilePhoto;
		this.bio = bio;
		this.role = "subscriber";

	}
    //methods

	public void setUserId(int userId) {
        this.userId = userId;
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

	protected void setProfilePhoto(InputStream profilePhoto) {
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

	public InputStream getProfilePhoto() {
		return profilePhoto;
	}

	public String getBio() {
		return bio;
	}
	
	public int getUserId() {
		return this.userId;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password + ", role=" + role + ", profilePhoto="
				+ profilePhoto + ", bio=" + bio + "]";
	}
	
	
}