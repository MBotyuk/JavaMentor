<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 31.12.2019
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/users">
    <p> Имя <input type="text" name="firstName"> </p>
    <p> Фамилия <input type="text" name="secondName"> </p>
    <p> e-mail <input type="email" name="email"> </p>
    <p> Пароль <input type="password" name="password"> </p>
    <input type="submit" value="Add">
</form>
<c:forEach var="user" items="${users}">
<table width = 60% border = 1px>
    <tr>
        <td border = 1px>
            <c:out value="${user}"/>
        </td>
        <td width = 20% border = 1px>
            <form action="${pageContext.request.contextPath}/users" method="post">
                <input type="hidden" name="userId" value=${user.id}>
                <input type="submit" name="button" value="Edit">
                <input type="submit" name="button" value="Delete">
            </form>
        </td>
    </tr>
</table>
</c:forEach>
</body>
</html>
