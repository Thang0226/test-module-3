package project.model;

import java.time.LocalDate;

public class CallCard {
	private String borrowID;
	private int bookID;
	private int studentID;
	private boolean state;
	private LocalDate borrowDate;
	private LocalDate returnDate;

	public CallCard(String borrowID, int bookID, int studentID, boolean state, LocalDate borrowDate,
	                LocalDate returnDate) {
		this.borrowID = borrowID;
		this.bookID = bookID;
		this.studentID = studentID;
		this.state = state;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public String getBorrowID() {
		return borrowID;
	}

	public void setBorrowID(String borrowID) {
		this.borrowID = borrowID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
}
