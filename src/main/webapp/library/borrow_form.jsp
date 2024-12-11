<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 11/12/24
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Create New Student</title>
  <link rel="stylesheet" href="../styles/bootstrap.min.css">
</head>

<body class="bg-light py-5">

<div class="container">
  <!-- Page Title -->
  <h1 class="text-primary text-center mb-4">Borrow Book Form</h1>

  <!-- Form Section -->
  <form method="post" action="library?action=update_book"
        class="p-4 bg-white border rounded col-md-12 col-lg-6 mx-auto">
    <fieldset>
      <legend class="text-primary">Student Information</legend>

      <input type="hidden" name="book_id" value="${requestScope.book.id}" />
      <div class="mb-3">
        <label for="borrow_id" class="form-label">Borrow ID:</label>
        <input type="text" class="form-control" name="borrow_id" id="borrow_id">
      </div>

      <div class="mb-3">
        <label for="book_name" class="form-label">Book Name:</label>
        <input type="text" class="form-control" name="book_name" id="book_name" value="${requestScope.book.name}" disabled>
      </div>

      <div class="mb-3">
        <label for="student_id" class="form-label">Student Name:</label>
        <select class="form-select" name="student_id" id="student_id">
          <c:forEach var="student" items="${requestScope.students}">
            <option value="${student.id}">${student.fullName}</option>
          </c:forEach>
        </select>
      </div>

      <div class="mb-3">
        <label for="borrow_date" class="form-label">Borrow Date:</label>
        <input type="text" class="form-control" id="borrow_date" name="borrow_date" value="${requestScope.date}"
               disabled>
      </div>

      <div class="mb-3">
        <label for="return_date" class="form-label">Return Date:</label>
        <input type="text" class="form-control" id="return_date" name="return_date" placeholder="dd/MM/yyyy">
      </div>

      <div>
        <button class="btn btn-primary" type="submit" onclick="validateReturnDate()">
          Borrow
        </button>
        <button class="btn btn-secondary" onclick="confirmCancel()">
          Cancel
        </button>
      </div>
    </fieldset>
  </form>
</div>

<script src="../styles/bootstrap.bundle.min.js"></script>
<script>
  // Get the borrow date from the input field
  const borrowDateInput = document.getElementById('borrow_date');
  const returnDateInput = document.getElementById('return_date');

  // Format the borrow date to dd/MM/yyyy
  const borrowDate = new Date(borrowDateInput.value);
  const borrowDateFormatted = borrowDate.toLocaleDateString('en-GB'); // 'en-GB' format is dd/MM/yyyy
  borrowDateInput.value = borrowDateFormatted;

  // Function to validate the return date
  function validateReturnDate() {
    const returnDateStr = returnDateInput.value;

    // Check if the entered return date is in the correct format (dd/MM/yyyy)
    const returnDateParts = returnDateStr.split('/');
    if (returnDateParts.length === 3) {
      const returnDate = new Date(`${returnDateParts[1]}/${returnDateParts[0]}/${returnDateParts[2]}`);

      if (returnDate < borrowDate) {
        alert('Return date cannot be earlier than the borrow date.');
        returnDateInput.value = ''; // Clear the return date if it's invalid
      }
    }
  }

  // Function to confirm cancel action
  function confirmCancel() {
    const isConfirmed = confirm("Are you sure you want to cancel?");
    if (isConfirmed) {
      // Redirect or perform any other action on cancel
      window.history.back();
    }
  }
</script>
</body>
</html>
