<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 23.01.2020
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/tildafavicon.ico"/>"/>
    <title>Edit</title>
</head>
<body>
<form class="style" action="/admin/edit" name="user" method="POST">
    <p class="heading">Edit User</p>
    <p><input type="text" name="name" placeholder="name" value="${user.name}" maxlength="100" required autofocus
              pattern="^[^\s]+(\s.*)?$">
    <p><input type="email" name="email" placeholder="email" value="${user.email}" maxlength="40" required>
    <p class="error"><c:out value="${error}"/></p>
    <p><input type="password" name="password" placeholder="password" value="" maxlength="20">
    <p>
    <p><select size="3" name="role">
        <option disabled>Выберите роль</option>
        <option selected value="${roleOne}">${roleOne}</option>
        <option value="${roleTwo}">${roleTwo}</option>
    </select></p>
        <input type="submit" value="Edit">
    </p>
</form>
</body>
</html>