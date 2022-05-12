package com.highradius.Manager;
import com.highradius.Modal.User;
import com.highradius.Modal.User1;
import java.util.*;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.highradius.Manager.Manager;

public class MainApp {
	
	public static void main(String args[]) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user=(User)context.getBean("user");
		
		Manager manager=(Manager)context.getBean("movieManager");
		
		
//	   user.setTitle("AAAAA");
//	   user.setRelease(2025);
//	   user.setSpecial("Deleted Scenes");
//	   user.setRating("PG");
//	   user.setLanguage(1);
//	   user.setDirector("ANish");
//	   user.setDescription("Thril");
	   
//	   manager.deleteMovie(1050);
		
		
//	   manager.addMovie(user);
		
//		List<User1>list=new ArrayList<User1>();
		
//		list=manager.getAllMovies(0, 5);
		
		
//	 System.out.println(list.size());
		
//		List<Integer>list1=new ArrayList<>();
//		
//		
//		list1.add(10);
//		list1.add(20);
//		list1.add(30);
//		list1.add(40);
//		
//		Iterator it=list1.iterator();
//		
//	for(;it.hasNext();) {
//		System.out.println(it.next());
//		
//	}
		
//		user.setTitle("");
//		user.setRelease(2022);
//		user.setDirector("");
//		user.setLanguage(1);
//		
//	List<User1>list=manager.searchMovie(user);
//		
//	 Iterator it=list.iterator();
//	for(;it.hasNext();) {
//		User1 u=(User1)it.next();
//		
//	  System.out.println(u.getRating());
//		
//	}
		
		manager.getAllMovies(0, 10);
		
	}

}
