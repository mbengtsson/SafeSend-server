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
<body>
<h1>HTTP Status 403 - Forbidden</h1>

<c:choose>
    <c:when test="${empty username}">
        <h2>Access denied!</h2>
    </c:when>
    <c:otherwise>
        <h2>Access denied!</h2>

        <h3>Insufficient privileges, <i>${username}</i> is not an administrator</h3>
    </c:otherwise>
</c:choose>

</body>
</html>
