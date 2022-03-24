<%--
  Created by IntelliJ IDEA.
  User: duong
  Date: 17/03/22
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
    </script>
</head>
<body>
<div style="background-color: aquamarine; text-align: center">
    <div>
        <h1>Drink Management</h1>
    </div>
    <div>
        <h2 style="color: crimson">Edit Drink</h2>
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
    <div>
        <c:if test="${drink != null}">
            <input type="hidden" name="drinkIdD" value="<c:out value='${drink.drinkIdD}'/>"/>
        </c:if>
    </div>
    <div class="form-row">
        <p class="form-group col-sm-1"></p>
        <div class="form-group col-sm-2" style="margin-left: 500px">
            <b for="Type">Type</b>
            <select name="typeD" id="Type" class="form-control" style="width: 250px">
                <option selected value="CAFE">CAFE</option>
                <option value="TEA">TEA</option>
                <option value="YOGURT">YOGURT</option>
                <option value="JUICE">JUICE</option>
                <option value="SMOOTHIE">SMOOTHIE</option>
                <option value="OTHER">OTHER</option>
            </select>
        </div>
        <div class="form-group col-sm-3" style="margin-left: 500px">
            <div>
                <b for="Name">Name Update</b>
                <input value="<c:out value='${drink.drinkNameD}'/>" name="drinkNameD" id="Name" type="text"
                       class="form-control" style="width: 250px">
            </div>
            <div style="color: red"><%=messErrorName%></div>
        </div>
        <div class="form-group col-sm-1" style="margin-left: 500px">
            <b for="Price">Price Update</b>
            <input name="priceD"
                   id="Price" data-toggle="tooltip" data-placement="top"
                   title="Only Number and Less than 100,0000,000"
                   type="text" class="form-control" style="width: 250px">
            <div style="width: 200px; color: red"> <%=messErrorPrice%></div>
        </div>
    </div>
    <div class="form-row" style="margin-left: 500px">
        <button type="buttom" class="btn btn-success form-group col-sm-1">UPDATE</button>
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>
