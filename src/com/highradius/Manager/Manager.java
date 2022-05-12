package com.highradius.Manager;

import java.util.List;
import com.highradius.Modal.User1;
import com.highradius.Modal.User;
import com.highradius.Modal.User3;

public interface Manager {
	
	public void addMovie(User user);
	public List<User1>getAllMovies(int start,int limit);
	public void editMovie(Integer id,User user);
	public void deleteMovie(List<Integer>id);
	
	public long getTotal();
	public List<User1> searchMovie(User user);
	
	public void intro();

}
