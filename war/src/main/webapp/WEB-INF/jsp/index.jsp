<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-20
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Safe-send</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>

</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h2>${message}</h2>

    <a href="<%=request.getContextPath()%>/user.html">User page</a><br>
    <a href="<%=request.getContextPath()%>/admin.html">Administration page</a>
    <br>
    <br>
    <a href="<%=request.getContextPath()%>/api/">API documentation</a>
    <br>
    <br>
    <a href="https://stjerneman.com/safesend.apk">Download android-app</a>


</div>
</body>
</html>
