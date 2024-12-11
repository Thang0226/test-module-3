package project.controller;

import project.DAO.BookDAO;
import project.DAO.StudentDAO;
import project.model.Book;
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

@WebServlet(name = "LibraryServlet", urlPatterns = "/library")
public class LibraryServlet extends HttpServlet {
	BookDAO bookDAO = new BookDAO();
	StudentDAO studentDAO = new StudentDAO();

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
			case "update_book":
				updateBook(req, resp);
				break;
			case "delete":

				break;
			case "view":

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
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String date = localDate.format(formatters);
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

	private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookId = Integer.parseInt(req.getParameter("book_id"));
		Book book = bookDAO.findById(bookId);
		int current_count = book.getCount();
		book.setCount(current_count - 1);
		bookDAO.update(book);
		listBooks(req, resp);
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
			case "create":

				break;
			case "update_book":
				updateBook(req, resp);
				break;
			case "delete":

				break;
			case "search":

				break;
			default:
				break;
		}
	}
}
