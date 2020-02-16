<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 21.01.2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/tildafavicon.ico"/>"/>
    <title>Delete User</title>
</head>
<body>
<form class="style" action="/admin/delete" name="user" method="POST">
<table class="style">
    <caption class="heading">User delete</caption>
    <tr>
        <th class="left-side">id</th>
        <th style="width: 100%">name</th>
        <th>email</th>
        <th>password</th>
        <th colspan="2" class="right-side"></th>
    </tr>
        <tr>
            <td class="left-side">${user.id}</td>
            <td class="title">${user.name}</td>
            <td>${user.email}</td>
            <td>
            </td>
            <td class="right-side">
            </td>
        </tr>
</table>
    <p>
        <input type="submit" value="OK">
    </p>
</form>
</body>
</html>