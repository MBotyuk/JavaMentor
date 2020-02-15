<%--
  Created by IntelliJ IDEA.
  User: mboty
  Date: 31.12.2019
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/logOutServlet" method="get">
    <button>LogUot</button>
        <table border=1px>
            <tr>
                <td border=1px>
                    <c:out value="${user}"/>
                </td>
            </tr>
        </table>
</form>
</body>
</html>
