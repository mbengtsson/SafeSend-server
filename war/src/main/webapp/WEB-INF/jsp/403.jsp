<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-20
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Safe-send - 403 Forbidden</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>

</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h1>HTTP Status 403 - Forbidden</h1>

    <c:choose>
        <c:when test="${empty username}">
            <h2>Access denied!</h2>
        </c:when>
        <c:otherwise>
            <h2>Access denied!</h2>

            <h3><i><c:out value="${username}"/></i> has insufficient privileges to access this resource</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
