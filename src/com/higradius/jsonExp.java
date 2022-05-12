package com.higradius;
import java.util.*;

import com.google.gson.Gson;

public class jsonExp {
	
	public static void main(String args[]) {
		User ob=new User();
		
		ob.setTitle("Iron Man");
		ob.setDescription("This is the beginning");
		ob.setRelease(2022);
		ob.setLanguage("English");
		ob.setDirector("Avanish");
		ob.setRating("PG");
		ob.setSpecial("Thriller");
		
	  User ob1=new User();
	  	ob1.setTitle("TIME");
		ob1.setDescription("This is the beginning");
		ob1.setRelease(2022);
		ob1.setLanguage("English");
		ob1.setDirector("Avanish");
		ob1.setRating("PG");
		ob1.setSpecial("Thriller");
		
		
		List<User>list=new ArrayList<>();
		
		list.add(ob);
		list.add(ob1);
		
		
		
		Resp resp=new Resp(true,2000,list);
		
		Gson gson=new Gson();
		
		System.out.println(gson.toJson(resp));
		
	}

}
