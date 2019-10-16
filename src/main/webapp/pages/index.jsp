<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/style.css">
    <title>Document</title>

</head>
<body>
    <div class="container-fluid" id="main-screen">
        <nav class="navbar navbar-expand-lg">
                <a class="navbar-brand d-none d-sm-block" href="#" style="color:#fff;">Time Tracking</a>
                <ul class="mr-auto">

                </ul>
                <div class="form-inline pull-right" id="login-bar">

                  <a href="${pageContext.request.contextPath}/site/login_page" class="btn">Вход</a>
                    <a href="${pageContext.request.contextPath}/site/sign_up_page" class="btn btn-inverse">Регистрация</a>
                </div>

        </nav>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 order-2 order-sm-1">
                    <h1><fmt:message key="index.h1"/></h1>
                    <p class="lead"><fmt:message key="index.p"/></p>
                </div>
                <div class="col-sm-6 order-1 order-sm-2">
                    <img src="${pageContext.request.contextPath}/pages/image/main.png" alt="">
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>