<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 04.02.2020
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<h1>Hi ${username}!<h2></h2>
    <img src="https://i.gifer.com/fyDA.gif" align="center" width="300px" alt="pic"><br/>
    <form action="/" method="GET">
        <input type="submit" value="Перейти на главную">
    </form>
    <form action="/logout">
        <input type="submit" value="Выйти">
    </form>
</body>
</html>

