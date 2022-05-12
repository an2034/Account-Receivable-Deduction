package com.highradius.DAO;
import com.highradius.Modal.User;
import com.highradius.Modal.User3;
import com.highradius.Modal.User1;
import java.util.List;
import java.util.*;


public interface MovieDAO {
	
	
	public void addMovie(User user);

	public List<User1> getAllMovies(int start,int limit);
	public void editMovie(Integer id,User user);
	public void deleteMovie(List<Integer> id);
	
	public long getTotal();
	
	public List<User1> searchMovie(User user);
	
	 
} 
