package com.highradius.Modal;





public class User1 {
	
	private int id;
	private String title;
	private int release;
	private String special;
	private String rating;
	private String language;
	private String director;
	private String description;
	
	
	
	
	
	public User1() {}
	public User1(int id, String title, int release, String special, String rating, String language, String director,String description) {

		this.id = id;
		this.title = title;
		this.release = release;
		this.special = special;
		this.rating = rating;
		this.language = language;
		this.director = director;
		this.description = description;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRelease() {
		return release;
	}
	public void setRelease(int release) {
		this.release = release;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
