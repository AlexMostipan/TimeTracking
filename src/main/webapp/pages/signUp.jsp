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
    <title>Document</title>
</head>
<body class="bg-color">
<div class="container-fluid">
    <div class="row justify-content-md-center my-5">
        <div class="col-md-6 card shadow">
            <div class="card-body">
                <h2 class="text-muted"><fmt:message key="sign_up.title"/></h2>
                <form action="${pageContext.request.contextPath}/site/sign_up_page/sign_up" class="form-signup"
                      id="signupform">
                    <label for="login" class="mb-0 text-muted">
                        <fmt:message key="sign_up.username"/>
                    </label>

                    <input type="text" name="username" id="login" class="form-control px-2" required autofocus="">

                    <label for="pass" class="mb-0 text-muted">
                        <fmt:message key="sign_up.pass"/>
                    </label>
                    <input type="password" name="password" id="pass" class="form-control px-2" required autofocus="">


                    </label>

                    <label for="email" class="mb-0 text-muted">
                        <fmt:message key="sign_up.email"/>
                    </label>
                    <input type="email" name="email" id="email" class="form-control px-2" autofocus="">
                    <div class="my-4">
                        <div class="row">
                            <div class="col-sm-6">
                                <a href="${pageContext.request.contextPath}/site/home_page"
                                   class="btn btn-danger btn-block">
                                    <fmt:message key="sign_up.btn.home"/>
                                </a>
                            </div>
                            <div class="col-sm-6">
                                <button class="btn btn-success btn-block" type="submit">
                                    <fmt:message key="sign_up.btn.create"/>
                                    <c:if test="${not empty email}">
                                        <script>alert("Form submitted");
                                        </script></c:if>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function alertSignUp() {
        alert("Пользователь успешно создан !");
    }
</script>

</body>
</html>
