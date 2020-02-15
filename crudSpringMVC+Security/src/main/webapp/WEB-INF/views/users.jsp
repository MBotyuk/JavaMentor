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
    <title>Users</title>
</head>
<body>
<table class="style">
    <caption class="heading">Users</caption>
    <tr>
        <th class="left-side">id</th>
        <th style="width: 100%">name</th>
        <th>email</th>
        <th>password</th>
        <th colspan="2" class="right-side">action</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td class="left-side">${user.id}</td>
            <td class="title">${user.username}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>
                <a href="/edit/${user.id}"><span class="icon icon-edit"></span></a>
            </td>
            <td class="right-side">
                <a href="/delete/${user.id}"><span class="icon icon-delete"></span></a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7" class="left-side link right-side">
            <a style="margin-right: 70px; font-size: 100%" href="<c:url value="/add"/>">
                <span class="icon icon-add"></span>Add new User
            </a>
        </td>
    </tr>
</table>
</body>
</html>