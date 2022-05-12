package com.highradius.Manager;
import java.util.List;
 
import org.springframework.transaction.annotation.Transactional;
 
import com.highradius.DAO.MovieDAO;

import com.highradius.Modal.User;
import com.highradius.Modal.User1;
import com.highradius.Modal.User3;

import org.springframework.beans.factory.annotation.Autowired;
public class Managerimpl implements Manager
{
    //Employee dao injected by Spring context

    private MovieDAO movieDAO;
     
    public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}
    public MovieDAO getMovieDAO() {
		return movieDAO;
	}
    

	
    @Override
    
    public List<User1>searchMovie(User user) {
    	return movieDAO.searchMovie(user);
    }
	//This method will be called when a employee object is added
    @Override
    public void intro() {
    	System.out.println("HellO");
    }
    
    
    @Override
    @Transactional
    
    public long getTotal() {
    	return movieDAO.getTotal();
    }
    @Override
//    @Transactional
    public void addMovie(User user) {
    	
        movieDAO.addMovie(user);
    }
     
    //This method return list of employees in database
    
    @Override
    @Transactional
    
    public List<User1> getAllMovies(int start ,int limit) {
        return movieDAO.getAllMovies(start,limit);
    }
    
    @Override
//    @Transactional
    public void editMovie(Integer id,User user) {
    	
    	movieDAO.editMovie(id,user);
    }
    //Deletes a employee by it's id
    @Override
//    @Transactional
    public void deleteMovie(List<Integer>id) {
        movieDAO.deleteMovie(id);
    }
     
    //This setter will be used by Spring context to inject the dao's instance
    


}