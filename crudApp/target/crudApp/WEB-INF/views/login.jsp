<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 31.12.2019
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/">
    <p> e-mail <input type="email" name="email" required></p>
    <p> Password <input type="password" name="password" required></p>
    <input type="submit" value="Log in">
</form>
</body>
</html>
