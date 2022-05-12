package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class DeleteServlet
 */
//@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String url="jdbc:mysql://localhost:3306/sakila";
		String name="root";
		String pass="root";
		
		
		String title=request.getParameter("title");
		
		
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pass);
			
		
			String query="delete from film where title=?;";
			PreparedStatement st=con.prepareStatement(query);
			
			st.setString(1, title);
			
			
			int count=st.executeUpdate();
						
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		doGet(request, response);
	}

}
