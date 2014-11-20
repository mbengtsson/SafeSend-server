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
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"  />

</head>
<body>
<h1>${title}</h1>
<h2>${message}</h2>

<a href="<%=request.getContextPath()%>/user.html">User page</a><br>
<a href="<%=request.getContextPath()%>/admin.html">Administration page</a>

<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
    <!-- For login user -->
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            User : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Logout</a>
        </h2>
    </c:if>


</sec:authorize>
</body>
</html>
