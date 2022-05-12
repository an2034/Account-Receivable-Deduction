package com.highradius.Action;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.highradius.Manager.Manager;
import com.highradius.Modal.User;
import com.highradius.Modal.User1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


import java.util.*;
//import com.opensymphony.xwork2.ModelDriven;
public class SearchAction extends ActionSupport  {
	private static final long serialVersionUID = -3827439829486925185L;
	
	
	private String title;
	private String release;
	private String language;
	private String director;
	
	
	private List<User1>list;
	private boolean success;
	private long total;
	



	public List<User1> getList() {
		return list;
	}



	public void setList(List<User1> list) {
		this.list = list;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
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
	

	
	
	//methods
	
	public String search() throws Exception {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		
		User user=(User)context.getBean("user");
//		
		Manager manager=(Manager)context.getBean("movieManager");
		   user.setTitle(title);
		   user.setRelease(Integer.parseInt(release));
		   user.setLanguage(Integer.parseInt(language));
		   user.setDirector(director);
		   
		   

	 System.out.println(title);
	 System.out.println(release);
	 System.out.println(language);
	 System.out.println(director);
		   
		  list= manager.searchMovie(user);
		  
		  total=manager.getTotal();
		  success=true;
		return Action.SUCCESS;
	}
}
