package com.java.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {

	public ResultSet fetchDataById(String uname, String password) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String SELECT_STUDENTS_SQL = "SELECT * FROM student WHERE username=? and password=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/services", "root", "123456");
			statement = connection.prepareStatement(SELECT_STUDENTS_SQL);
			statement.setString(1, uname);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			System.out.println("Result set"+resultSet);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return resultSet;
	}

}
