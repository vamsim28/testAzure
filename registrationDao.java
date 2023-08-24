package com.java.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registrationDao {

	public int fetchDataById(String username, String password, String gender, String email, String contact ) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int rowcount = 0;

		String SELECT_STUDENTS_SQL = "INSERT INTO  users (uname,upassword,ugender, uemail, umobile)	values(?,?,?,?,?) ";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/services", "root", "123456");
			statement = connection.prepareStatement(SELECT_STUDENTS_SQL);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, gender);
			statement.setString(4, email);
			statement.setString(5, contact);
			
			rowcount = statement.executeUpdate();
			
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  rowcount ;
	}
	
	
}
