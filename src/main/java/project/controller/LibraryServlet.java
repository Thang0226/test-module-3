package project.controller;

import project.DAO.BookDAO;
import project.DAO.CallCardDAO;
import project.DAO.StudentDAO;
import project.model.Book;
import project.model.CallCard;
import project.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "LibraryServlet", urlPatterns = "/library")
public class LibraryServlet extends HttpServlet {
	BookDAO bookDAO = new BookDAO();
	StudentDAO studentDAO = new StudentDAO();
	CallCardDAO callCardDAO = new CallCardDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			action = "";
		}
		switch (action) {
			case "borrow":
				showBorrowForm(req, resp);
				break;
			default:
				listBooks(req, resp);
				break;
		}
	}

	private void showBorrowForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookId = Integer.parseInt(req.getParameter("book_id"));
		Book book = bookDAO.findById(bookId);
		if (book.getCount() == 0) {
			req.setAttribute("message", "Error! Book is out of stock!");
			listBooks(req, resp);
		} else {
			req.setAttribute("book", book);
			List<Student> students = studentDAO.findAll();
			req.setAttribute("students", students);

			LocalDate localDate = LocalDate.now();
//			LocalDate localDate = LocalDate.parse("2020-02-20");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String date = localDate.format(formatter);
			req.setAttribute("date", date);

			RequestDispatcher dispatcher = req.getRequestDispatcher("library/borrow_form.jsp");
			try {
				dispatcher.forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}

	private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = bookDAO.findAll();
		req.setAttribute("books", books);

		RequestDispatcher dispatcher = req.getRequestDispatcher("library/book_list.jsp");
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action == null) {
			action = "";
		}
		switch (action) {
			case "update_book":
				validateInput(req, resp);
				break;
			default:
				break;
		}
	}

	private void validateInput(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String borrowID = req.getParameter("borrow_id");
		String returnDate = req.getParameter("return_date");

		Pattern patternBorrowID = Pattern.compile("^MS-[0-9]{4}$");
		Matcher matcherBorrowID = patternBorrowID.matcher(borrowID);
		Pattern patternDate = Pattern.compile("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$");
		Matcher matcherDate = patternDate.matcher(returnDate);

		if (matcherBorrowID.matches() && matcherDate.matches()) {
			boolean cardExisted = callCardDAO.findByBorrowID(borrowID) != null;
			boolean validDate = checkDate(returnDate);
			if (cardExisted) {
				req.setAttribute("message", "Borrow ID is already used!");
			} else if (!validDate) {
				req.setAttribute("message", "Return date cannot be before today!");
			} else {
				updateBook(req, resp);
			}
		} else {
			req.setAttribute("message", "Invalid input!");
		}
		showBorrowForm(req, resp);
	}

	private boolean checkDate(String returnDate) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(returnDate, formatter);
		return date.isAfter(currentDate) || date.isEqual(currentDate);
	}

	private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookId = Integer.parseInt(req.getParameter("book_id"));
		Book book = bookDAO.findById(bookId);
		int current_count = book.getCount();
		book.setCount(current_count - 1);
		bookDAO.update(book);

		String borrowID = req.getParameter("borrow_id");
		int studentId = Integer.parseInt(req.getParameter("student_id"));
		String borrowDate = req.getParameter("borrow_date");
		String returnDate = req.getParameter("return_date");
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate borrowLocalDate = LocalDate.parse(borrowDate, formatters);
		LocalDate returnLocalDate = LocalDate.parse(returnDate, formatters);
		boolean state = true;
		CallCard card = new CallCard(borrowID, bookId, studentId, state, borrowLocalDate, returnLocalDate);
		callCardDAO.add(card);

		listBooks(req, resp);
	}
}
