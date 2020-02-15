<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 12.02.2020
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Index</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>

        <h4><a href="/logout">Выйти</a></h4>

    <h4><a href="/admin">Пользователи (только админ)</a></h4>
    <h4><a href="/user">Пользователь (админ и пользователь)</a></h4>
</div>
</body>
</html>
