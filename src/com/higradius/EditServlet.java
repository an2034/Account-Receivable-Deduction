package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;


/**
 * Servlet implementation class EditServlet
 */
//@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		PrintWriter out = response.getWriter();
		String url="jdbc:mysql://localhost:3306/sakila";
		String name="root";
		String pass="root";
		
		
		int id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		int release=Integer.parseInt(request.getParameter("release"));
		String special=request.getParameter("special");
		String rating=request.getParameter("rating");
		int language=Integer.parseInt(request.getParameter("language"));
		String director=request.getParameter("director");
		String description=request.getParameter("description");
		
	
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pass);
			
		
			String query="update film set title=?,description=?,release_year=?,language_id=?,rating=?,special_features=?,director=? where film_id=?;";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, title);
			st.setString(2, description);
			st.setInt(3, release);
			st.setInt(4, language);
			st.setString(5,rating);
			st.setString(6,special);
			st.setString(7, director);
			st.setInt(8, id);
			
			int count=st.executeUpdate();
						
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
