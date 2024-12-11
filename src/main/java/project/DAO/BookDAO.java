package project.DAO;

import project.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IDAO<Book> {
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
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call list_books()}")
		) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				int count = rs.getInt("count");
				Book book = new Book(id, name, author, description, count);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return books;
	}

	@Override
	public boolean add(Book object) {
		return false;
	}

	@Override
	public Book findById(int id) {
		Book book = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_book(?)}")
		) {
			cstmt.setInt(1, id);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				int count = rs.getInt("count");
				book = new Book(id, name, author, description, count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return book;
	}

	@Override
	public boolean update(Book book) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call update_book(?,?,?,?,?)}")
		) {
			cstmt.setInt(1, book.getId());
			cstmt.setString(2, book.getName());
			cstmt.setString(3, book.getAuthor());
			cstmt.setString(4, book.getDescription());
			cstmt.setInt(5, book.getCount());
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Update failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}
}
