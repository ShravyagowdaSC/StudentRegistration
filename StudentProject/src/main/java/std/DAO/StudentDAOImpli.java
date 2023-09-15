package std.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import std.module.Student;

public class StudentDAOImpli implements StudentDA{

	static private final String URL = "jdbc:mysql://localhost:3306/student";
	static private final String USERNAME = "root";
	static private final String PASSWORD = "root";
	
	static private Connection connection = null;
	
	static 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("connection started");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int save(Student s) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1, s.getName());
			prepareStatement.setString(2, s.getGender());
			prepareStatement.setString(3, s.getEmail());
			prepareStatement.setLong(4, s.getPhone());
			prepareStatement.setString(5, s.getBranch());
			prepareStatement.setDate(6, s.getDOB());
			prepareStatement.setFloat(7, s.getPercentage());
			return prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Student s) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, s.getName());
			prepareStatement.setString(2, s.getGender());
			prepareStatement.setString(3, s.getEmail());
			prepareStatement.setLong(4, s.getPhone());
			prepareStatement.setString(5, s.getBranch());
			prepareStatement.setDate(6, s.getDOB());
			prepareStatement.setFloat(7, s.getPercentage());
			return prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Student s) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, s.getId());
			return prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int delete(int id) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setInt(1, id);
			return prepareStatement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Student get(int id) {
		Statement createStatement = null;
		Student student = null;
		try {
			createStatement = connection.createStatement();
			ResultSet resultSet = createStatement.executeQuery(SELECT_QUERY);
			
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String gender = resultSet.getString("gender");
				String email = resultSet.getString("email");
				long phone = resultSet.getLong("phone");
				String branch = resultSet.getString("branch");
				Date DOB = resultSet.getDate("dOB");
				float percentage = resultSet.getFloat("percentage");
				
				student = new Student(id, name, gender, email, phone, branch, DOB, percentage);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public List<Student> getAll() {
		Statement createStatement = null;
		ResultSet resultSet = null;
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			createStatement = connection.createStatement();
			resultSet = createStatement.executeQuery(SELECT_ALL_QUERY);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String gender = resultSet.getString("gender");
				String email = resultSet.getString("email");
				long phone = resultSet.getLong("phone");
				String branch = resultSet.getString("branch");
				Date DOB = resultSet.getDate("dOB");
				float percentage = resultSet.getFloat("percentage");
				
				Student student = new Student(id, name, gender, email, phone, branch, DOB, percentage);
				students.add(student);
				
			} 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}

}
