package project.DAO;

import project.model.CallCard;

import java.sql.*;
import java.util.List;

public class CallCardDAO implements IDAO<CallCard> {
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
	public List<CallCard> findAll() {
		return null;
	}

	@Override
	public boolean add(CallCard card) {
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call add_card(?,?,?,?,?)}")
		) {
			cstmt.setInt(1, card.getBookID());
			cstmt.setInt(2, card.getStudentID());
			cstmt.setBoolean(3, card.isState());
			cstmt.setString(4, card.getBorrowDate().toString());
			cstmt.setString(5, card.getReturnDate().toString());
			int rowAffected = cstmt.executeUpdate();
			if (rowAffected == 0) {
				throw new SQLException("Insert failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public CallCard findById(int id) {
		return null;
	}

	@Override
	public boolean update(CallCard object) {
		return false;
	}

	@Override
	public boolean remove(int id) {
		return false;
	}
}
