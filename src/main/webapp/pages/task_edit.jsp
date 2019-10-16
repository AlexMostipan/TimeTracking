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

                   <a href="${pageContext.request.contextPath}/pages/index.jsp" class="btn btn-inverse"><i class="fas fa-sign-out-alt"></i> Выход</a>
                </div>

        </nav>
            <div class="row" style="margin-right:0;">
                <div class="col-sm-2 d-none d-sm-block" id="left-menu">
                    <ul class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/site/admin_page">Все активности</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/finished_admin">Архив</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/during_admin">В работе</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/requests">Заявки на добавления активности</a></li>
                        <li><a href="${pageContext.request.contextPath}/site/create_activity_page">Создать активность</a></li>

                    </ul>

                </div>
                <div class="col-sm-10">
                    <ul class="list-unstyled activity-table">
                        <li class="activity-item">
                            <div class="row">
                                <!--Активность начало-->
                                <div class="col-sm-12">
                                    <h1>Создание активности</h1>
                                    <form action="${pageContext.request.contextPath}/site/create_activity_page/create_activity">
                                            <div class="form-group row">
                                        <div class="col-sm-2">
                                                <label for="task-user">Исполнитель</label>
                                            </div>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="username" id="task-user">
                                                        <c:forEach var="user" items="${requestScope.allUsers}">
                                                            <option value="<c:out value="${user.username}"/>"><c:out value="${user.username}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                    </div>
                                        <div class="form-group row">
                                            <div class="col-sm-2">
                                                <label for="task-user">Тип активности</label>
                                            </div>
                                            <div class="col-sm-10">
                                                <select class="form-control" name="title" id="task-activity_type">
                                                    <c:forEach var="activityType" items="${requestScope.allActivityTypes}">
                                                        <option value="${activityType.id}">${activityType.title}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-2">
                                                <label for="task-desc">Описание</label>
                                            </div>
                                            <div class="col-sm-10">
                                                    <textarea class="form-control" name="description" id=task-desc"></textarea>
                                            </div>
                                        </div>
                                        <button class="btn btn-success btn-lg mx-auto d-block" onclick="alertCreating()" >Создать активность</button>

                                    </form>
                                </div>
                                <!--Активность конец-->
                            </div>

                        </li>
                    </ul>
                </div>
            </div>
    </div>
    <script>
        function alertCreating() {
            alert("Активность была успешно добавлена!");
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>

