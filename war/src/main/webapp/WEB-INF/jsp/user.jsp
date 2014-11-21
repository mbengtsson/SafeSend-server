<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-20
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Safe-send - user page</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>

</head>
<body>

<jsp:include page="header.jsp"/>

<h2>Welcome ${displayName}</h2>

<c:choose>
    <c:when test="${not empty messages}">
        <h3>You have ${messagesLength} new messages</h3>

        <table class="list">
            <tr>
                <th>From</th>
                <th></th>
                <th>Date</th>
            </tr>
            <c:forEach items="${messages}" var="message">
                <tr>
                    <td>${message.senderName}</td>
                    <td>(${message.senderEmail})</td>
                    <td>${message.time}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h3>Sorry, you have no new messages</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
