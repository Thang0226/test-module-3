<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 11/12/24
  Time: 09:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Management system</title>
  <link rel="stylesheet" href="../styles/bootstrap.min.css">
</head>
<body>
<div class="container text-center">
  <h1>
    Book List
  </h1>
</div>
<div class="container">
  <!-- Message Section -->
  <p class="text-center">
    <c:if test='${requestScope.message != null}'>
      <span class="text-success fw-bold">${requestScope.message}</span>
    </c:if>
  </p>

  <div class="table-responsive">
    <table class="table table-bordered table-light table-striped table-hover">
      <thead class="table-light">
      <tr>
        <th scope="col" class="col-1">ID</th>
        <th scope="col" class="col-2">Book Name</th>
        <th scope="col" class="col-2">Author</th>
        <th scope="col" class="col-1">Count</th>
        <th scope="col" class="col-3">Description</th>
        <th scope="col" class="col-1">Action</th>
      </tr>
      </thead>
      <tbody class="table-group-divider">
      <c:forEach items="${requestScope.books}" var="book">
        <tr>
          <td>
              ${book.id}
          </td>
          <td>
            ${book.name}
          </td>
          <td>
              ${book.author}
          </td>
          <td>
            ${book.count}
          <td>
            ${book.description}
          </td>
          <td>
            <button class="btn btn-primary">
              <a href="library?action=borrow&book_id=${book.id}" style="text-decoration: none; color: white">Borrow</a>
            </button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<script src="../styles/bootstrap.bundle.min.js"></script>
</body>
</html>
