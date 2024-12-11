package project.DAO;

import project.model.Book;
import project.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IDAO<Student> {
	private String jdbcURL = "jdbc:mysql://localhost:3306/book_library";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_students()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String className = rs.getString("class_name");
				Student student = new Student(id, fullName, className);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return students;
	}

	@Override
	public boolean add(Student object) {
		return false;
	}

	@Override
	public Student findById(int id) {
		return null;
	}

	@Override
	public boolean update(Student object) {
		return false;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}
}
