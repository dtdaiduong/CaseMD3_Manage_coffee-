<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
    <title>Add new Drink</title>
</head>
<body>
<ul class="nav nav-pills">
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="">HOME</a>
    </li>

    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="" role="button"
           aria-expanded="false">Users</a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="">Create-Account</a></li>
            <li><a class="dropdown-item" href="">Edit-Acc</a></li>
            <li><a class="dropdown-item" href="">List-Acccount</a></li>

        </ul>
    </li>

    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="" role="button"
           aria-expanded="false">Products</a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item"
                   href="">Create-Products </a></li>
            <li><a class="dropdown-item" href="">Edit-products</a>
            </li>
            <li><a class="dropdown-item" href="">List-Products</a>
            </li>

        </ul>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="">Orders</a>
    </li>

</ul>

<h1>Create new customer</h1>
<p>
    <c:if test='${requestScope["message"] != null }'>
        <span class="message" style="color: green">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/customer/list.jsp">Backs to customer list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Customer information</legend>
        <table class="table table-hover">
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" id="address"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
