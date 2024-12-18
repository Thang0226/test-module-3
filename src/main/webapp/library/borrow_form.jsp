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
  <title>Create New Borrow Card</title>
  <link rel="stylesheet" href="../styles/bootstrap.min.css">
</head>

<body class="bg-light py-5">

<div class="container">
  <!-- Page Title -->
  <h1 class="text-primary text-center mb-4">Borrow Book Form</h1>

  <!-- Message Section -->
  <p class="text-center">
    <c:if test='${requestScope.message != null}'>
      <span class="text-success fw-bold">${requestScope.message}</span>
    </c:if>
  </p>

  <!-- Form Section -->
  <form method="post" action="library?action=update_book"
        class="p-4 bg-white border rounded col-md-12 col-lg-6 mx-auto">
    <fieldset>
      <legend class="text-primary">Student Information</legend>

      <input type="hidden" name="book_id" value="${requestScope.book.id}" />
      <div class="mb-3">
        <label for="borrow_id" class="form-label">Borrow ID:</label>
        <input type="text" class="form-control" name="borrow_id" id="borrow_id" placeholder="MS-XXXX">
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
               readonly>
      </div>

      <div class="mb-3">
        <label for="return_date" class="form-label">Return Date:</label>
        <input type="text" class="form-control" id="return_date" name="return_date" placeholder="dd/MM/yyyy">
      </div>

      <div>
        <button class="btn btn-primary" type="submit">
          Borrow
        </button>
        <a href="#" class="btn btn-secondary" id="cancelLink" onclick="confirmCancel(event)">
          Cancel
        </a>
      </div>
    </fieldset>
  </form>
</div>

<script src="../styles/bootstrap.bundle.min.js"></script>
<script>
  // Function to confirm cancel action
  function confirmCancel(event) {
    // Prevent the default behavior of the link
    event.preventDefault();

    // Show a confirmation dialog
    if (confirm("Are you sure you want to cancel?")) {
      // If confirmed, redirect to the /library page
      window.location.href = "/library";
    } else {
      // If not confirmed, do nothing (stay on the page)
      return false;
    }
  }
</script>
</body>
</html>
