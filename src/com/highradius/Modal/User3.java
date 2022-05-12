package com.highradius.Modal;
public class User3 {
	
	private int id;
	private String title;
	private int release;
	private String special;
	private String rating;
//	private int language;
	private String director;
	private String description;
	
	private Language languages;
	
	
	
	public User3() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User3(int id, String title, int release, String special, String rating, String director,
			String description, Language languages) {
		super();
		this.id = id;
		this.title = title;
		this.release = release;
		this.special = special;
		this.rating = rating;
	
		this.director = director;
		this.description = description;
		this.languages = languages;
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


	public Language getLanguages() {
		return languages;
	}


	public void setLanguages(Language languages) {
		this.languages = languages;
	}
	
	
	
	
	
	
}