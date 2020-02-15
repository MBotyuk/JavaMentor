<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 31.01.2020
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="name" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <div>
            <form:input type="password" path="passwordConfirm"
                        placeholder="Confirm your password"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <div>
            <form:input type="email" path="email"
                        placeholder="email"></form:input>
            <form:errors path="email"></form:errors>
                ${emailError}
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <br>
    <a href="/">Главная</a>
</div>
</body>
</html>
