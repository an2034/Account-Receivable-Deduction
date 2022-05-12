
  package com.highradius.Action; 
  import com.highradius.Manager.Manager; 
  import com.highradius.Modal.User1;
  import java.sql.*;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport; 
  import org.springframework.context.ApplicationContext; 
  import org.springframework.context.support.ClassPathXmlApplicationContext; 
  import java.util.List; 
  import java.util.*;
  
  public class JsonAction extends ActionSupport{
  
  private static final long serialVersionUID = 1L; 
  
 
  
  int start; int limit;
  
  //parameters to be send as json 
  
  
  List<User1> list = null; 
  boolean success=false;
  long total;
  
  public int getStart() { 
	  return start; 
	  }
  
  public void setStart(int start) { 
	 this.start= start;
	 }
  
  public int getLimit() { 
	  return limit; 
	  }
  
  
  public void setLimit(int limit) { 
	  this.limit= limit; 
	  }
  
  
  public String fetch() throws Exception{
  
//  list=new ArrayList<User>();
 
  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
  
   Manager manager=(Manager)context.getBean("movieManager");
   
   list=manager.getAllMovies(start, limit);
   
  	total=manager.getTotal(); 
  	success=true;
    return Action.SUCCESS; 
    }
   
  
  
  public List<User1> getList() 
  { 
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
  
  
  }
 