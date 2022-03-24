<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    <title>Danh Sách Thức Uống</title>
</head>
<body>
<h1>Danh Sách Thức Uống</h1>
<p>
    <a href="display/view.jsp">Thêm cái chi vô nha</a>
</p>
<table border="1" class="table table-hover">
    <tr>
        <td>ID</td>
        <td>Tên sản phẩm</td>
        <td>Giá</td>
        <td>Ghi chú</td>
        <td>Ảnh minh họa</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${drinks}' var="drink">
        <tr>
            <td>${drink.drinkId}</td>
            <td>${drink.drinkName}</td>
            <td>${drink.price}</td>
<%--            <td><a href="/customers?action=view&id=${customer.getId()}">${customer.getName()}</a></td>--%>
<%--            <td>${customer.getEmail()}</td>--%>
<%--            <td>${customer.getAddress()}</td>--%>
<%--            <td><a href="/customers?action=edit&id=${customer.getId()}">Edit</a></td>--%>
<%--            <td><a href="/customers?action=delete&id=${customer.getId()}">Delete</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
