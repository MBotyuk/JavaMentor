<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 05.01.2020
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/add">
    <p> Имя <input type="text" name="firstName" required></p>
    <p> Фамилия <input type="text" name="secondName" required></p>
    <p> e-mail <input type="email" name="email" required></p>
    <p> Пароль <input type="password" name="password" required></p>
    <input type="submit" value="Add">
</form>
<form method="GET" action="${pageContext.request.contextPath}/users">
    <input type="submit" value="List">
</form>
</body>
</html>
