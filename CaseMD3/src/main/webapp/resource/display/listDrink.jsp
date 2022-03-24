<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="row mt-5">
    <div class="col-1 align-content-lg-start">
        <a href="/drinks?">Go To Home</a>
    </div>
    <div class="col-2 align-content-lg-start" style="width: 350px">
        <form method="post" action="/drinks?action=search" class="float-right">
            <input type="text" placeholder="Search" name="nameSearch" required>
            <input type="submit" value="Search">
        </form>
    </div>

</div>

<div class="row mt-2">
    <div class="col-1 align-content-lg-start">
        <a href="/drinks?action=create" class="btn btn-success">Add new Drink</a>
    </div>
    <div class="col-2 align-content-lg-start">
        <a href="/drinks?action=showDeActive" class="btn btn-secondary">List of products out of stock</a>
    </div>
<%--    <div class="col-2 align-content-lg-start">--%>
<%--        <a href="/drinks?action=showActive" class="btn btn-primary"></a>--%>
<%--    </div>--%>
</div>

<table class="table table-secondary table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th>Drink Type</th>
        <th scope="col">Name Drink</th>
        <th scope="col">Price</th>
        <th scope="col" style="width: 20%;">Status</th>
        <th scope="col" colspan="2" style="text-align: center"> Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="drink" items="${drinkList}" >
        <tr>
            <td>${drink.drinkIdD}</td>
            <td>${drink.typeD}</td>
            <td>${drink.drinkNameD}</td>
            <td>${drink.priceD}</td>
            <td style="width: 20%;">${drink.statusD}</td>
            <td style="text-align: center">
                <a href="/drinks?action=edit&drinkId=${drink.drinkIdD}" type="button" class="btn btn-info">Update</a>
            </td>
            <td style="text-align: center">
                <a href="/drinks?action=stop&drinkId=${drink.drinkIdD}" type="button" class="btn btn-danger">Change Status</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
