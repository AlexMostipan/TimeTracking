<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.css">
    <title>Time Tracking</title>
</head>
<body>
    <div class="container-fluid" id="user-screen">
        <nav class="navbar navbar-expand-lg navbar-light bg-dark">
                <a class="navbar-brand d-none d-sm-block" href="#" style="color:#fff;">Time Tracking</a>
                <ul class="mr-auto">

                </ul>
                <div class="form-inline pull-right" id="login-bar">
                    <a href="/" class="btn">Мой профиль</a>
                    <a href="/" class="btn btn-inverse"><i class="fas fa-sign-out-alt"></i> Выход</a>
                </div>

        </nav>
            <div class="row" style="margin-right:0;">
                <div class="col-sm-2 d-none d-sm-block" id="left-menu">
                    <ul class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/site/during_user">В работе</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/finished_user">Архив</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/user_page">Все активности</a></li>
                        
                    </ul>

                </div>
                <div class="col-sm-10">
                        <h2 class="text-center">Редактировать профиль</h2>
                        <form class="w-50 mx-auto">
                                <div class="form-group">
                                  <label for="login">Логин</label>
                                  <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                          <div class="input-group-text">@</div>
                                        </div>
                                        <input type="text" class="form-control" id="login" value="username">
                                      </div>
                                </div>
                                <div class="form-group">
                                        <label for="name">E-mail</label>
                                        <input type="email" class="form-control" id="login" placeholder="E-mail">
                                </div>
                                <div class="form-group">
                                        <label for="password">Пароль</label>
                                        <input type="password" class="form-control" id="password" placeholder="Пароль">
                                </div>
                                <div class="form-group">
                                        <label for="password-2">Повторите пароль</label>
                                        <input type="password" class="form-control" id="password-2" placeholder="Пароль">
                                </div>
                                <button type="submit" class="btn btn-primary btn-lg w-100">Внести изменения</button>
                                
                                
                              </form>
                </div>
            </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
