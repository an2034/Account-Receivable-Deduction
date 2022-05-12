package com.higradius;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import java.sql.*;



/**
 * Servlet implementation class FetchServlet
 */
//@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String url="jdbc:mysql://localhost:3306/sakila";
		String name="root";
		String pass="root";
		List<User>list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pass);
			
			int start=Integer.parseInt(request.getParameter("start"));
			int lim=Integer.parseInt(request.getParameter("limit"));
		   
//			System.out.println(start);
//			System.out.println(lim);
			
			String query="SELECT film_id,title,DESCRIPTION,release_year,NAME,director,rating,special_features FROM film JOIN LANGUAGE ON film.language_id=language.language_id order by title limit ?,?;";
		
	
			
			PreparedStatement st=con.prepareStatement(query);
			
			st.setInt(1, start);
		    st.setInt(2, lim);
			ResultSet rs=st.executeQuery();
		    
		
			while(rs.next()) {
				
				
				int id=rs.getInt(1);
				String title=rs.getString(2);
				String description=rs.getString(3);
				int release=rs.getInt(4);
				String language=rs.getString(5);
				String director=rs.getString(6);
				String rating=rs.getString(7);
				String special=rs.getString(8);
				
				User ob=new User();
				
				ob.setId(id);
				ob.setTitle(title);
				ob.setDescription(description);
				ob.setRelease(release);
				ob.setLanguage(language);
				ob.setDirector(director);
				ob.setRating(rating);
				ob.setSpecial(special);
				
				list.add(ob);
			
			}
			
			
		}  
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Resp resp=new Resp(true,1009,list);
		 

		  Gson gson = new Gson();
		  response.setContentType("application/json");

		  out.println(gson.toJson(resp));
		  
	
		  out.flush();

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
