<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Safe-send - user-details</title>
    <link href="<%=request.getContextPath()%>/style/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="content">
    <h2>Administration - user-details</h2>
    <a href="<%=request.getContextPath()%>/admin.html">Back</a>
    <br>
    <br>
    <form:form commandName="user">
        <table class="list">
            <tr>
                <th>Id:&nbsp;</th>
                <td>${user.id}</td>
            </tr>
            <tr>
                <th>Email:&nbsp;</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>Display-name:&nbsp;</th>
                <td>${user.displayName}</td>
            </tr>
            <tr>
                <th>Role:&nbsp;</th>
                <td>
                    <form:select path="role">
                        <form:options items="${roles}"/>
                    </form:select>
                    <a href="javascript:{}" onclick="document.getElementById('user').submit(); return false;">Change</a>

                </td>
            </tr>
        </table>
    </form:form>

    <div id="key">
        <h3>Public PGP-Key</h3>

        <pre>${user.publicKey}</pre>
    </div>

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
                <td>${logItem.timeStamp}</td>
                <td>${logItem.verb}</td>
                <td>${logItem.objectType}</td>
                <td>${logItem.actorId}</td>
                <td>${logItem.targetId}</td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
