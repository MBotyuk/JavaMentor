<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 31.01.2020
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="name" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <button type="submit">Log In</button>
        </div>
    </form>
</div>

</body>
</html>
