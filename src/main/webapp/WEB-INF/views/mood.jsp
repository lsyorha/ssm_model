<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yorha
  Date: 2023/4/1
  Time: 1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mood</title>
</head>
<body>

<div id="moods">
    <b>说说列表</b>
    <c:forEach items="${moods}" var="mood">
        --------------------------
        <br>
        <b>用户：</b><span id="account">${mood.userName}</span><br>
        <b>说说内容：</b><span id="content">${mood.content}</span><br>
        <b>发表时间：</b><span id="publish_time">${mood.publishTime}</span><br>
        <b>点赞数：</b><span id="praise_num">${mood.praiseNum}</span><br>
        <div style="margin-left: 350px">
<%--            传统点赞--%>
            <a id="praise" href="${pageContext.request.contextPath}/mood/${mood.id}/praise?userId=${mood.userId}">赞</a>
<%--        引入redis--%>
    <a id="praise" href="${pageContext.request.contextPath}/mood/${mood.id}/praiseForRedis?userId=${mood.userId}">赞</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
