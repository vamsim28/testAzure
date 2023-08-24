package com.java.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.services.model.house;

public class HousingDao {
	
		private String jdbcURL =  "jdbc:mysql://localhost:3306/services";
		private String jdbcUsername = "root";
		private String jdbcpassword ="123456";
		
		private static final String INSERT_STUDENTS_SQL = "INSERT INTO  housing" + "(firstName,lastName,studentId, type, numberOfPeople) "
													+ "values" +	"(?,?,?,?,?);";
		
		private static final String UPDATE_STUDENTS_SQL = "UPDATE housing set firstName =?,lastName =?,studentId =?, type =?, numberOfPeople =? where id = ?; ";
				
		
		private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * from housing";
		
		private static final String SELECT_STUDENT_BY_ID_SQL = "SELECT id, firstName, lastName, type, numberOfPeople from housing where id =?";
		
		private static final String DELETE_STUDENT_SQL = "delete from housing where id = ?;";
		
		
		protected Connection getConnection() {
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcpassword);
				}catch (SQLException e) {
					e.printStackTrace();
				}catch (ClassNotFoundException e) {
				e.printStackTrace();
				}
			return connection;
		}
		
		public int insertHousingData(house hos ) {
				Connection connection = null;
		
				
				PreparedStatement statement = null;
				ResultSet resultSet = null;
				int rowcount = 0;
				
				connection=	getConnection();
		
				try {
					
					statement = connection.prepareStatement(INSERT_STUDENTS_SQL);
					statement.setString(1, hos.getFirstName());
					statement.setString(2, hos.getLastName());
					statement.setString(3, hos.getStudentId());
					statement.setString(4, hos.getType());
					statement.setInt(5, hos.getNumberOfPeople());
					
					rowcount = statement.executeUpdate();
					
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
		public int updateHousingData(house hos) {
			Connection connection = null;
	
			
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			int rowcount = 0;
			
			connection=	getConnection();
	
			try {
				
				statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);
				statement.setString(1, hos.getFirstName());
				statement.setString(2, hos.getLastName());
				statement.setString(3, hos.getStudentId());
				statement.setString(4, hos.getType());
				statement.setInt(5, hos.getNumberOfPeople());
				
				rowcount = statement.executeUpdate();
				
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
		public house selectHousingRecord (int id)
		{
			house hos = null;
			Connection connect = getConnection();
			PreparedStatement statement = null;
			
			try {
				statement = connect.prepareStatement(SELECT_STUDENT_BY_ID_SQL);
				statement.setInt(1, id);
				ResultSet set = statement.executeQuery();
				while(set.next())
				{
					String firstName = set.getString("firstName");
					String lastName = set.getString("lastName");
					String studentId = set.getString("StudentId");
					String type = set.getString("type");
					int numberOfPersons = set.getInt("numberOfPeople");
					
					hos = new house(firstName, lastName, studentId, type, numberOfPersons);	
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			return hos;
			
		}

		public List<house> selectAllHousingRecords ()
		{
			house hos = null;
			Connection connect = getConnection();
			PreparedStatement statement = null;
			List<house> houseList = new ArrayList<house>();
			
			try {
				statement = connect.prepareStatement(SELECT_ALL_STUDENTS_SQL);
				ResultSet set = statement.executeQuery();
				while(set.next())
				{
					int id = set.getInt("id");
					String firstName = set.getString("firstName");
					String lastName = set.getString("lastName");
					String studentId = set.getString("StudentId");
					String type = set.getString("type");
					int numberOfPersons = set.getInt("numberOfPeople");
					hos = new house(id,firstName, lastName, studentId, type, numberOfPersons);	
					houseList.add(hos);
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			return houseList;
			
		}

		public boolean deleteHousingRecord (int id)
		{
			Connection connect = getConnection();
			PreparedStatement statement = null;
			boolean rowDeleted = false;
			
			try {
				statement = connect.prepareStatement(DELETE_STUDENT_SQL);
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() >0;
				
				
				} catch (SQLException e) {
					e.printStackTrace();
				}

			return rowDeleted;
			
		}













}
