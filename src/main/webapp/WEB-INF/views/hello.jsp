<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yorha
  Date: 2023/3/31
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<c:forEach var="user" items="${list}">
    <p>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.account}</td>
    </p>
</c:forEach>
</body>
</html>
