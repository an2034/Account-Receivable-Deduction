package com.highradius.Action;
import com.highradius.DAO.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.highradius.Manager.Manager;
import com.highradius.Modal.User;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;
public class MovieAction extends ActionSupport  {
	private static final long serialVersionUID = -3827439829486925185L;
	
	
	
	

	private String id;
	private String title;
	private String release;
	private String special;
	private String rating;
	private String language;
	private String director;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
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
	
	//methods
	
	public String add() throws Exception {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user=(User)context.getBean("user");
		
		Manager manager=(Manager)context.getBean("movieManager");
		   user.setTitle(title);
		   user.setRelease(Integer.parseInt(release));
		   user.setSpecial(special);
		   user.setRating("PG");
		   user.setLanguage(Integer.parseInt(language));
		   user.setDirector(director);
		   user.setDescription(description);
		   
		   
           System.out.println(user.getTitle());
		   
		   System.out.println(user.getRelease());
		   
		   
		  manager.addMovie(user);

		return "registered";
	} 
	
	public String edit() throws Exception {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user=(User)context.getBean("user");
		
		Manager manager=(Manager)context.getBean("movieManager");
		
		
		   user.setTitle(title);
		   user.setRelease(Integer.parseInt(release));
		   user.setSpecial(special);
		   user.setRating("PG");
		   user.setLanguage(Integer.parseInt(language));
		   user.setDirector(director);
		   user.setDescription(description);	
		   
		  
		   manager.editMovie(Integer.parseInt(id),user);
		return "updated";
	} 
	public String delete() throws Exception {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		User user=(User)context.getBean("user");
		Manager manager=(Manager)context.getBean("movieManager");
		
		
		
		List<Integer>array=new ArrayList<Integer>();
		
		String str="";
		
		
		for(int i=0;i<id.length();i++) {		
			
			if(i==id.length()-1) {
					str+=id.charAt(i);
					array.add(Integer.parseInt(str));
				}
			
	  else if(id.charAt(i)!=',')
			str+=id.charAt(i);
		
		else if(id.charAt(i)==',') {
			
			array.add(Integer.parseInt(str));
			str="";
		}
	
	 }
		
	
		
		
	    manager.deleteMovie(array);
		return "deleted";
	} 
	
}
