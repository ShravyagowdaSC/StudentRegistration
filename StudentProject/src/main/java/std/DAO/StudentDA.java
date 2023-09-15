package std.DAO;

import java.util.List;

import std.module.Student;

public interface StudentDA {
	
	String INSERT_QUERY = "INSERT into `student` (`name`, `gender`, `email`, `phone`, `branch`, `dOB`,`percentage`) values (?, ?, ?, ?, ?, ?, ?)";
	
	String UPDATE_QUERY = "UPDATE `student` SET `name` = ?, `gender` = ?, `email` = ?, `phone` = ?, `branch` =?, `DOB` = ?, `percentage` = ? WHERE `id` = ?";
	
	String DELETE_QUERY = "DELETE `student` WHERE `id` = ?";
	
	String SELECT_QUERY = "SELECT * from `student` WHERE `id` = ?";
	
	String SELECT_ALL_QUERY = "SELECT * from `student`";

	
	 int save(Student s);
	 int update(Student s);
	 int delete(Student s); // s.id
	 int delete(int id);
	 Student get(int id);
	 List<Student> getAll();
}
