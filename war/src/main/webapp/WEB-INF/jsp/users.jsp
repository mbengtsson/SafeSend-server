<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-23
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Safe-send - users</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h2>Administration - users</h2>
    <a href="<%=request.getContextPath()%>/admin.html">Back</a>
    <br>
    <br>
    <table class="list">
        <tr>
            <th>Id</th>
            <th>Email</th>
            <th>Display-name</th>
            <th>Role</th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.displayName}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><a href="<%=request.getContextPath()%>/admin/users/${user.id}.html">details/edit</a></td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
