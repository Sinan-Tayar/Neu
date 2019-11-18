package com.google.gwt.sample.stockwatcher.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.sample.stockwatcher.shared.bo.Group;

import sharedShoppingList.server.db.DBConnection;

public class GroupMapper {

	private static GroupMapper groupMapper = null;
	
    /*Der Konstruktur duch "protected" dafür, dass nur eine Instanz existieren kann*/
	public GroupMapper() {}
	
	public static GroupMapper groupMapper() {
		if (groupMapper == null) {
			groupMapper = new GroupMapper();
		}
		return groupMapper;
	}
	
	public Group insert (Group group) {
		Connection con = DBConnection.connection();

    
		String sql= "INSERT INTO kinogruppe (name) VALUES ('"+ group.getName()+ "')";  

		
	    try {
	    	/*
	    	 * Einstellung dass autoincremented ID's zureuckgeliefert werden
	    	 */
	    	
	    	PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	    	int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating group failed, no rows affected.");
	        }
	        
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                group.setId(generatedKeys.getInt(1)); //index 1 = id column
	            }
	            else {
	                throw new SQLException("Creating group failed, no ID obtained.");
	            }
	        }
	      
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return group;
	}
	public void delete (Group group) {
		Connection con = DBConnection.connection();
			
			String sql="DELETE FROM kinogruppe WHERE id = " + group.getId();
			
		    try {
		    	
		    	Statement stmt = con.createStatement();
		    	stmt.executeUpdate(sql);	 
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			
		}
		
	
}
