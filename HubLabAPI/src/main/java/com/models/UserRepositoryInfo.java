package com.models;

public class UserRepositoryInfo {

	private String fullName;
	private String htmlURL;
	private String ownerName;

	public UserRepositoryInfo(String fullName, String htmlURL, String ownerName) {
		super();
		this.fullName = fullName;
		this.htmlURL = htmlURL;
		this.ownerName = ownerName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHtmlURL() {
		return htmlURL;
	}

	public void setHtmlURL(String htmlURL) {
		this.htmlURL = htmlURL;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
