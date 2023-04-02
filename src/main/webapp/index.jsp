<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3>hello,SSM</h3>
<p><a href="${pageContext.request.contextPath}/user/findAll">hello</a></p>
<p><a href="${pageContext.request.contextPath}/mood/findAll">mood</a></p>
<p><a href="${pageContext.request.contextPath}/user/find">find(1)</a></p>
</body>
</html>