<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static org.example.demo4.entity.User.catId" %>
<%@ page import="org.example.demo4.entity.Category" %>
<%@ page import="org.example.demo4.servlets.DB" %>
<%@ page import="org.example.demo4.entity.Product" %>
<%@ page import="org.example.demo4.auth.AuthService" %>
<%@ page import="org.example.demo4.entity.User" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Online Market</title>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .category-list a {
            margin-bottom: 10px;
        }

        .product-card {
            margin-bottom: 20px;
        }

        .basket-btn {
            position: sticky;
            top: 10px;
            z-index: 1000;
        }
    </style>
</head>
<body>
<%
    Optional<User> user = AuthService.loggedUser(request);
    if (user.isEmpty()) {
%>
<form action="/login.jsp">
    <button class="btn btn-primary">login</button>
</form>
<%
} else {
%>
<form action="/user/orders" method="post">
    <button class="btn btn-success">
        My Orders(
        <%=DB.ORDERS.stream().filter(order -> order.getUserId().equals(user.get().getId())).count()%>
        )
    </button>
</form>
<%
    }
%>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <h4>Categories</h4>
            <ul class="list-group category-list">
                <li>
                    <a href="/category/choose?categoryId=0"
                       class="list-group-item <%= catId.equals(0) ? "active" : "" %>">
                        All
                    </a>
                </li>
                <% for (Category category : DB.CATEGORIES) { %>
                <li>
                    <a href="/category/choose?categoryId=<%=category.getId()%>"
                       class="list-group-item <%= catId != null && catId.equals(category.getId()) ? "active" : "" %>">
                        <%= category.getName() %>
                    </a>
                </li>
                <% } %>
            </ul>
        </div>


        <div class="col-md-9">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1 class="text-primary">Products</h1>
                <form action="/basket.jsp" class="basket-btn">
                    <button class="btn btn-dark">
                        Basket (<%= DB.basket.size() %>)
                    </button>
                </form>
            </div>

            <div class="row">
                <%
                    List<Product> productList = new ArrayList<>(DB.PRODUCTS);
                    if (!catId.equals(0)) {
                        productList = DB.PRODUCTS.stream().filter(product -> product.getCategoryId().equals(catId)).toList();
                    }
                    for (Product product : productList) {
                %>
                <div class="col-md-4 product-card">
                    <div class="card">
                        <img src="/img/<%= product.getId() %>" class="card-img-top" alt="Product Image"
                             style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title"><%= product.getName() %>
                            </h5>
                            <p class="card-text text-success fw-bold">$<%= product.getPrice() %>
                            </p>
                            <form action="/add/basket" method="post">
                                <button class="btn <%= DB.basket.containsKey(product) ? "btn-success" : "btn-primary" %>"
                                        name="productId" value="<%= product.getId() %>">
                                    <%= DB.basket.containsKey(product) ? "Selected" : "Select" %>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qyrTcCU6q2XlKuvz19gW/4URGRxJTjBSozZn7f4Md3O9QxkEXZOIEvsR7BxCL8x9"
        crossorigin="anonymous"></script>
</body>
</html>
