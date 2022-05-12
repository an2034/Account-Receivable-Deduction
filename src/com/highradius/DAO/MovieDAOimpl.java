
package com.highradius.DAO; 
import java.util.List;
import com.highradius.Modal.User1;
import com.highradius.Modal.User;
import com.highradius.Modal.User3;
import com.highradius.Modal.Language;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.*;

import com.highradius.Modal.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MovieDAOimpl implements MovieDAO
{
    

	
    private SessionFactory sessionFactory;
     
	 @SuppressWarnings("deprecation")
	 
	 
	 @Override
	 public long getTotal() {
		 sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        
	        long count=0;
	      
	      try {
	        
	    	  tx = session.beginTransaction();
	          String hql = "SELECT count(id) FROM User";
	         Query query = session.createQuery(hql);
	         List <Integer>results = query.list();
	         Iterator it=results.iterator();
	        
	        count=(long) it.next();
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		 
		 
		 return count;
		 
	 }
	 
	 @Override
	 public List<User1> getAllMovies(int start,int limit){
		 
		 
		 List lst;
		 
		 List<User1>lst1=new ArrayList<User1>();
//		HashMap<Integer,String> map=new HashMap<>();
//		
//		map.put(1,"English");
//		map.put(2, "Italian");
//		map.put(3, "Japanese");
//		map.put(4, "Mandarin");
//		map.put(5, "French");
//		map.put(6, "German");
//		map.put(7, "Mongolian");
		
		
		
		 
		 
		 sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
		      
		      try {
		         tx = session.beginTransaction();
		         Query query = session.createQuery("FROM User3 order by release desc"); 
		         
		         query.setFirstResult(start);
		         query.setMaxResults(limit);
		         lst=query.list();
		         
		         for (Iterator iterator = lst.iterator(); iterator.hasNext();){
		            User3 user3 = (User3) iterator.next(); 
		             Language lan=user3.getLanguages();
		             
		             ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
			          User1 user1=(User1)context.getBean("user1");
			            
		            user1.setId(user3.getId());
		            user1.setTitle(user3.getTitle());
		            user1.setRelease(user3.getRelease());
		            user1.setSpecial(user3.getSpecial());
		            user1.setRating(user3.getRating());

		            
		            user1.setLanguage(lan.getName());
		            user1.setDescription(user3.getDescription());
		            user1.setDirector(user3.getDirector());
		            lst1.add(user1); 
		        	 
		          	 
//		        	 System.out.println(user3.getId()+" "+user3.getTitle()+" "+user3.getRelease()+" "+user3.getSpecial()+" "+user3.getRating()+" "+lan.getName()+" "+user3.getDescription()+" "+user3.getDirector());
		   
		        	
		         }
		         
		         
		         tx.commit();
		        
		     } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return lst1;
	 }
	 
	 
	 
	 @Override
	 public List<User1> searchMovie(User user) {
		 
		 List<User1> lst1=new ArrayList<User1>();
		 List lst = null;
		
		 
		   sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         
		         if(user.getTitle().length()>0) {
		        	 Query query = session.createQuery("FROM User3  WHERE title like"+"'%"+user.getTitle()+"%'"+ " order by release desc");
		        	 
//			         query.setFirstResult(start);
//			         query.setMaxResults(limit);
			         lst=query.list();
			         
					
				 }
				 
				 else if(user.getDirector().length()>0) {
					 Query query = session.createQuery("FROM User3  WHERE director like "+"'%"+user.getDirector()+"%'"+ " order by release desc"); 
			         
//			         query.setFirstResult(start);
//			         query.setMaxResults(limit);
			         lst=query.list();
			         
					 
				 }
				 else if(user.getRelease()!=0) {
					 Query query = session.createQuery("FROM User3  WHERE  release="+user.getRelease()+ " order by release desc"); 
			         
//			         query.setFirstResult(start);
//			         query.setMaxResults(limit);
			         lst=query.list();
			         
					 
				 }
		        	
				 else if(user.getLanguage()!=0) {
					 Query query = session.createQuery("FROM User3  WHERE languages.id ="+user.getLanguage()+ " order by release desc"); 
			         
//			         query.setFirstResult(start);
//			         query.setMaxResults(limit);
			         lst=query.list();
			         
					 
				 }
		         
		         
		         Language lan=null;
		         
		         for (Iterator iterator = lst.iterator(); iterator.hasNext();){
		            User3 use = (User3) iterator.next(); 
		            
		             ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
			          User1 user1=(User1)context.getBean("user1");
			          
		            lan=use.getLanguages();
		            
		            user1.setId(use.getId());
		            user1.setTitle(use.getTitle());
		            user1.setRelease(use.getRelease());
		            user1.setSpecial(use.getSpecial());
		            user1.setRating(use.getRating());
		            
		            user1.setLanguage(lan.getName());
		            
		            
		            user1.setDescription(use.getDescription());
		            user1.setDirector(use.getDirector());
		            lst1.add(user1); 
		         }
		         
		         
		         tx.commit();
		        
		     } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return lst1;
		 
		 
		 
	 }
		@Override
    public void addMovie(User user){
    	sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        
        try {
           tx = session.beginTransaction();
           session.save(user); 
           tx.commit();
        } catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        } finally {
           session.close(); 
        }
        
     }
	 
	 @Override
	 public void editMovie(Integer id, User user ){
		 	sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	        User use = (User)session.get(User.class,id); 
	        
	        use.setTitle(user.getTitle());
	        use.setRelease(user.getRelease());
	        use.setSpecial(user.getSpecial());
	        use.setRating(user.getRating());
	        use.setLanguage(user.getLanguage());
	        use.setDirector(user.getDirector());
	        use.setDescription(user.getDescription());
	        
	        
	         
			 session.update(use); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	 
	 
	 
	 
	 @Override
	 public void deleteMovie(List<Integer> id){
		 sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	      
	      try {
	    	  
	    for(Integer x:id) {
	         tx = session.beginTransaction();
	        User user = (User)session.get(User.class, x); 
	         session.delete(user);
	       
	         tx.commit();
	    }
	        
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
 
}