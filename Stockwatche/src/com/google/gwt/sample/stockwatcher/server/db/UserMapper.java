package com.google.gwt.sample.stockwatcher.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.google.gwt.sample.stockwatcher.shared.bo.User;




/**
* Dieser Mapper ist für alle Datenbankvorgänge - also der Informationsabfrage aus der DB, sowie der Datenablage in der DB - des BOs User verantwortlich.
* Er ermöglicht die Durchführung aller "CRUD-Vorgänge". Dazu bietet er verschiedene Methoden.
* 
*/

public class UserMapper {
	private static UserMapper userMapper = null;
	
    /*Der Konstruktur duch "protected" dafür, dass nur eine Instanz existieren kann*/
	public UserMapper() {}
	
	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}
	
	/* Alle weiteren Methoden sind in 4 Blöcke eingeteilt (Create, Read, Updated, Delete)*/
	
	
/* CREATE (insert) - Dieser Block verfügt nur über eine Methode, die für alle Neueinträge verantwortlich ist*/
	
	public User insert (User user) {
		Connection con = DBConnection.connection();
		
		String sql= "INSERT INTO user (username, name, email) VALUES ('" + user.getName() + "','" + user.getUsername() + "','" + user.getGmail()+ "')";
		
	    try {
	    	
	    	/*
	    	 * Einstellung dass autoincremented ID's zureuckgeliefert werden
	    	 */
	    	
	    	PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    	int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        
	        
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                user.setId(generatedKeys.getInt(1)); //index 1 = id column
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }	 
	      
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return user;
	}
	
	
/* READ (find) - Dieser Block sorgt für Ausgabe bestehender Datensätze.*/
	
	/* find all */
	public Vector<User> findAll(){
		Connection con = DBConnection.connection();
		String sql = "SELECT * FROM user ORDER BY name";
		
		Vector<User> users= new Vector<User>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				User user = new User();

				user.setId(rs.getInt("iduser"));
				user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setGmail(rs.getString("email"));
				
				users.addElement(user);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		return users;
	}
	
	/* find by id */
	public User findById(int id) {
		Connection con = DBConnection.connection();
		User user = new User();
		String sql="SELECT * FROM user WHERE iduser=" + id;
			
		try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					
				user.setId(rs.getInt("iduser"));
				user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setGmail(rs.getString("email"));
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return user;
		}
	
	/* find by gmail 
	 * 
	 *  */
	public User findByGmail(String gmail) {
		
		Connection con = DBConnection.connection();
		String sql="SELECT * FROM user WHERE email='" + gmail+"'";
			
		try {

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				

				if (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("iduser"));
				user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setGmail(rs.getString("email"));
			
				return user;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		return null;
		}
	
	/* find by name */
	public User findByName(String name) {
		
		Connection con = DBConnection.connection();
		User user = new User();
		
		// DISTINCT sorg hier dafür, dass nur ein Nutzer-Objekt zurückgegeben wird
		
		
			
		try {
			
				String sql="SELECT * FROM user WHERE name= '" + name+"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					
				user.setId(rs.getInt("iduser"));
				user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setGmail(rs.getString("email"));
	
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return user;
		}
	
	public void delete (User user) {
		Connection con = DBConnection.connection();
			
			String sql= "DELETE FROM user WHERE iduser = " + user.getId();
			
		    try {
		    	
		    	Statement stmt = con.createStatement();
		    	stmt.executeUpdate(sql);	 
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			
		}
}
