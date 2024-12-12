package project.DAO;

import project.model.Book;
import project.model.CallCard;

import java.sql.*;
import java.time.LocalDate;
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
				CallableStatement cstmt = conn.prepareCall("{call add_card(?,?,?,?,?,?)}")
		) {
			cstmt.setString(1, card.getBorrowID());
			cstmt.setInt(2, card.getBookID());
			cstmt.setInt(3, card.getStudentID());
			cstmt.setBoolean(4, card.isState());
			cstmt.setString(5, card.getBorrowDate().toString());
			cstmt.setString(6, card.getReturnDate().toString());
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

	public CallCard findByBorrowID(String borrowID) {
		CallCard card = null;
		try (
				Connection conn = getConnection();
				CallableStatement cstmt = conn.prepareCall("{call find_card(?)}")
		) {
			cstmt.setString(1, borrowID);
			ResultSet rs = cstmt.executeQuery();
			if (rs.next()) {
				int bookID = rs.getInt("book_id");
				int studentID = rs.getInt("student_id");
				boolean state = rs.getBoolean("state");
				LocalDate borrowDate = LocalDate.parse(rs.getString("borrow_date"));
				LocalDate returnDate = LocalDate.parse(rs.getString("return_date"));
				card = new CallCard(borrowID, bookID, studentID, state, borrowDate, returnDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return card;
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
