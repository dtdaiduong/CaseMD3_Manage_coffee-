<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19/03/2022
  Time: 8:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order new product</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, Tahoma;
            font-size: 12px;

        }

        #main {
            width: 1300px;
            padding: 0;
            margin-left: auto;
            margin-right: auto;
        }

        #head {
            height: 100px;
            background-color: #F5F5F5;
            border: 1px solid #CDCDCD;
            margin-bottom: 5px;
        }

        #head-link {
            height: 50px;
            line-height: 30px;
            padding-left: 10px;
            padding-right: 10px;
            border: 1px solid #CDCDCD;
            background-color: #F5F5F5;
            margin-bottom: 5px;
            clear: both;
        }

        #left {
            width: 1%;
            min-height: 400px;
            border: 1px solid #CDCDCD;
            float: left;
            background-color: #85cac4;
            margin-bottom: 5px;
        }

        #content {
            width: 45%;
            min-height: 400px;
            border: 1px solid #CDCDCD;
            float: left;
            margin-left: 5px;
            background-color: #85cac4;
            margin-right: 5px;
            margin-bottom: 5px;
        }

        #right {
            width: 53%;
            min-height: 400px;
            border: 1px solid #CDCDCD;
            background-color: #85cac4;
            float: right;
            margin-bottom: 5px;
        }

        #footer {
            height: 50px;
            clear: both;
            border: 1px solid #CDCDCD;
            background-color: #747d7d;
        }
    </style>
</head>
<body>
<div id="main">
    <div id="head">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">COKE COLE</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                        aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarScroll">
                    <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/servletController">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                Option
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                                <li><a class="dropdown-item" href="/servletController?action=showProductLock">Product Lock</a></li>
                                <li><a class="dropdown-item" href="/servletController?action=showOrder">Order</a></li>
                                <li><a class="dropdown-item" href="#">Something else here</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex" method="get">
                        <input type="text" hidden name="action" value="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                               name="search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
    <div id="head-link">
        <div><h2>Order</h2></div>
    </div>
    <div id="left">
    </div>
    <div id="content">
        <%
            int idOrder = (int) request.getAttribute("idOrder");
        %>
        <table class="table table-secondary table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name Product</th>
                <th scope="col">Price</th>
                <th scope="col">Description</th>
                <th scope="col">Status</th>
                <th scope="col" style="text-align: center"> Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listProduct}" var="product">
                <tr>
                    <td>${product.idItem}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>${product.status}</td>
                    <td style="text-align: center">
                        <a href="/servletController?action=addProduct&idItem=${product.idItem}&idOrder=<%=idOrder%>"
                           type="button" class="btn btn-info">Add to order</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form method="post" action="/servletController?action=paidBill">
        <div id="right">
            <h1 style="text-align: center">NEW ORDER</h1><br>
            <a href="/servletController?action=cancelOrder&idOrder=<%=idOrder%>" type="button"class=" btn btn-danger" >Hủy đơn</a>
<%--            <input type="submit" value="Hủy đơn" class="btn btn-danger">--%>
            <hr>
            <table class="table table-secondary table-hover">
                <thead>
                <tr>
                    <%--                <th scope="col">Id Order</th>--%>
                    <th scope="col">Id item</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total Price</th>
                    <th scope="col" style="text-align: center">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${details}" var="orderDetail">
                    <tr>
                        <input type="hidden" name="idOrder" value="${orderDetail.orderID}"/>
                        <td>${orderDetail.idItem}</td>
                        <td>${orderDetail.priceItem}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.totalPrice}</td>
                        <td style="text-align: center">
                            <a href="/servletController?action=remove&idItem=${orderDetail.idItem}&idOrder=${orderDetail.orderID}"
                               type="button" class="btn btn-info">Remove</a>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
            <hr>
            <label style="font-size: x-large">Tổng tiền: ${totalPrice} VND </label> <br>
            <input type="hidden" name="totalPrice" value="${totalPrice}">
            <input type="submit" value="Thanh toán" class="btn btn-success">
        </div>
    </form>
    <div id="footer">
    </div>
</div>
</body>
</html>
