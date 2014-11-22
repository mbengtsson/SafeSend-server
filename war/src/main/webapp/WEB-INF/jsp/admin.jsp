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
    <title>Safe-send - administration page</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>

</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h2>Administration control-center</h2>
    <h3>Welcome ${displayName}</h3>
    <br>
    <a href="<%=request.getContextPath()%>/admin/users.html">User management</a><br>
    <a href="<%=request.getContextPath()%>/admin/log.html">Show log</a>

</div>
</body>
</html>
