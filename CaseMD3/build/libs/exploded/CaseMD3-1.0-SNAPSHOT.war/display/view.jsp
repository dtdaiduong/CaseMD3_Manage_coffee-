<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>View Drink</title>
</head>
<body>
<h1 style="color:darkred;">Drinks Details</h1>
<p>
    <a href="/display/list.jsp">Back to Drink list</a>
</p>
<table>
    <tr>
        <td>ID: </td>
        <td>${requestScope["drinks"].drinkId()}</td>
    </tr>
    <tr>
        <td>Name: </td>
        <td>${requestScope["drinks"].drinkName()}</td>
    </tr>
    <tr>
        <td>price : </td>
        <td>${requestScope["drinks"].price()}</td>
    </tr>


</table>
</body>
</html>
