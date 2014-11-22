<%--
  Created by IntelliJ IDEA.
  User: Marcus Bengtsson
  Date: 2014-11-21
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <div class="HeaderLeft">
        <h1><a href="<%= request.getContextPath() %>/index.html">safeSEND</a></h1>
    </div>
    <div class="HeaderRight">
        <sec:authorize access="hasAnyRole('USER', 'ADMIN')">
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
                <p>
                        ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
                </p>
            </c:if>
        </sec:authorize>
    </div>
    <div class="clear"></div>
</header>
