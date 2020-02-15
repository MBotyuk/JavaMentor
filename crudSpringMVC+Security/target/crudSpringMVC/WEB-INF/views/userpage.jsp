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
    <title>User personal page</title>
</head>
<body>
<h1>Hi ${username}!<h2></h2>
    <H2>You can do nothing on this page<br/>
        Just relax and look on this picture</H2>
    <img src="https://i.gifer.com/fyDA.gif" align="center" width="300px" alt="pic"><br/>
    <form action="/" method="GET">
        <input type="submit" value="Go back">
    </form>
    <form action="/logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
