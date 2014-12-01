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
    <a href="https://drive.google.com/file/d/0Bz9cm7WlxkCccFFVWDJ2QzZWVDA/view?usp=sharing">Download android-app</a>


</div>
</body>
</html>
