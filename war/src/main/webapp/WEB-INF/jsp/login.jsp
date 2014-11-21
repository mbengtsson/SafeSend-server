<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-20
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Safe-send - login Page</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>

</head>
<body onload='document.loginForm.username.focus();'>

<jsp:include page="header.jsp"/>
<div class="content">
    <div id="login-box">

        <h3>Login with Email and Password</h3>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form name='loginForm'
              action="<c:url value='/j_spring_security_check' />" method='POST'>

            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type='text' name='username'></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password'/></td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit"
                                           value="submit"/></td>
                </tr>
            </table>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </form>
    </div>
</div>
</body>
</html>
