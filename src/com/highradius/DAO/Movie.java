package com.highradius.DAO;
import java.sql.*;
import java.util.*;
import java.util.List;

public class Movie {

	// method for creating connection
	public static Connection myconnection() throws Exception {
		String url="jdbc:mysql://localhost:3306/sakila";
		String name="root";
		String pass="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, name,pass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String addUser(String title, String release, String special, String rating,String language,String director,String description) throws SQLException, Exception {
		
		String title1=title;
		int release1=Integer.parseInt(release);
		String special1=special;
		String rating1=rating;
		int language1=Integer.parseInt((language));
		String director1=director;
		String description1=description;
		
		try {
			String query="insert into film (title,description,release_year,language_id,rating,special_features,director)values(?,?,?,?,?,?,?);";
			PreparedStatement st = myconnection().prepareStatement(query);
			st.setString(1, title1);
			st.setString(2, description1);
			st.setInt(3, release1);
			st.setInt(4, language1);
			st.setString(5,rating1);
			st.setString(6,special1);
			st.setString(7, director1);
			st.executeUpdate();
			return "Registration Successful";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			if (myconnection() != null) {
				myconnection().close();
			}
		}
	}

	public ResultSet report(int start,int limit) throws SQLException, Exception {
		ResultSet rs = null;
		try {
			
			String query="SELECT film_id,title,DESCRIPTION,release_year,NAME,director,rating,special_features FROM film JOIN LANGUAGE ON film.language_id=language.language_id order by title limit ?,?";
			PreparedStatement st = myconnection().prepareStatement(query);
			
			st.setInt(1,start);
			st.setInt(2, limit);
			rs = st.executeQuery();
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (myconnection() != null) {
				myconnection().close();
			}
		}
	}

	public String updateUser(String id,String title, String release, String special, String rating,String language,String director,String description)
			throws SQLException, Exception {
		try {
			
			int id1=Integer.parseInt(id);
			String title1=title;
			int release1=Integer.parseInt(release);
			String special1=special;
			String rating1=rating;
			int language1=Integer.parseInt((language));
			String director1=director;
			String description1=description;
			
			String query="update film set title=?,description=?,release_year=?,language_id=?,rating=?,special_features=?,director=? where film_id=?;";
			PreparedStatement st = myconnection().prepareStatement(query);
			st.setString(1, title1);
			st.setString(2, description1);
			st.setInt(3, release1);
			st.setInt(4, language1);
			st.setString(5,rating1);
			st.setString(6,special1);
			st.setString(7, director1);
			st.setInt(8, id1);
			
			st.executeUpdate();
			return "Update Successful";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			if (myconnection() != null) {
				myconnection().close();
			}
		}
	}

	public String deleteUser(String title) throws SQLException, Exception {
		try {
			
			String title1=title;
			
			String query="delete from film where title=?;";
			PreparedStatement st = myconnection().prepareStatement(query);
			st.setString(1, title1);
			st.executeUpdate();
			return "Delete Successful";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			if (myconnection() != null) {
				myconnection().close();
			}
		}
	}
}