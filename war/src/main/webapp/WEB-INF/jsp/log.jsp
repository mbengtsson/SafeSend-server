<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-22
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Safe-send - log</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h2>Administration - log</h2>
    <a href="<%=request.getContextPath()%>/admin.html">Back</a>
    <br>
    <br>
    <table class="list">
        <tr>
            <th>TimeStamp</th>
            <th>Verb</th>
            <th>Object</th>
            <th>Actor</th>
            <th>Target</th>
        </tr>
        <c:forEach items="${log}" var="logItem">
            <tr>
                <td><c:out value="${logItem.timeStamp}"/></td>
                <td><c:out value="${logItem.verb}"/></td>
                <td><c:out value="${logItem.objectType}"/></td>
                <td><c:out value="${logItem.actorId}"/></td>
                <td><c:out value="${logItem.targetId}"/></td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
