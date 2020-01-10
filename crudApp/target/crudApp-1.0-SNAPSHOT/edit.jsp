<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 04.01.2020
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/edit">
            <input value=${user.id} type="hidden" name="id">
    <p> Имя <input value=${user.firstName} type="text" name="firstName"> </p>
    <p> Фамилия <input value=${user.secondName} type="text" name="secondName"> </p>
    <p> e-mail <input value=${user.email} type="email" name="email"> </p>
    <p> Пароль <input value=${user.password} type="password" name="password"> </p>
    <input type="submit" value="Save">
</form>
<form method="GET" action="${pageContext.request.contextPath}/users">
    <input type="submit" value="List">
</form>
</body>
</html>
