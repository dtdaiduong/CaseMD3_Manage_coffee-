<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Add new Drink</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
</head>
<body>
<div style="background-color: aquamarine">
    <div >
        <h1 style="text-align: center">Drink Management</h1>
    </div>
    <div style="color: crimson">
        <h2 class="mb-1" style="text-align: center">Add New Drink</h2>
    </div>
</div>
<h6>
    <a href="drinks?action=drinks">Go To Home</a>
</h6>
<hr>
<form method="post">
    <%
//        String drinkName = (String) request.getAttribute("drinkNameD");
//        if (drinkName == null) {
//            drinkName = "";
//        }
//        String price = (String) request.getAttribute("priceD");
//        if (price == null) {
//            price = "";
//        }
        String messErrorName = (String) request.getAttribute("messErrorName");
        String messErrorPrice = (String) request.getAttribute("messErrorPrice");
        if (messErrorName == null) {
            messErrorName = "";
        }
        if (messErrorPrice == null) {
            messErrorPrice = "";
        }
    %>
    <div class="form-row">
<%--        <p class="form-group col-sm-1">${exception}</p>--%>
        <div class="form-group col-sm-2">
            <b for="Type">Type</b>
            <select name="typeD" id="Type" class="form-control">
                <option selected value="CAFE">CAFE</option>
                <option value="TEA">TEA</option>
                <option value="YOGURT">YOGURT</option>
                <option value="JUICE">JUICE</option>
                <option value="SMOOTHIE">SMOOTHIE</option>
                <option value="OTHER">OTHER</option>
            </select>
        </div><x></x>
        <div class="form-group col-sm-3">
            <b for="Name">Name</b>
            <input name="drinkNameD" id="Name" type="text" class="form-control">
            <div style="color: red"><%=messErrorName%></div>

        </div>
        <div class="form-group col-sm-1" >
            <b for="Price">Price</b>
            <input name="priceD" id="Price" data-toggle="tooltip"
                   data-placement="top" type="text" style="width: 200px"
                   class="form-control">
            <div style="width: 200px;color: red"> <%=messErrorPrice%></div>
        </div>
    </div>
    <div class="form-row" style="margin-left: 500px">
        <button type="submit" class="btn btn-success form-group col-sm-1">ADD</button>
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</body>
</html>
