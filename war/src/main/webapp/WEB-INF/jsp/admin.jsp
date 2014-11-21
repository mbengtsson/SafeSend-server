<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-20
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Safe-send - administration page</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"  />

</head>
<body>

<jsp:include page="header.jsp"/>

<h2>${message}</h2>

</body>
</html>
