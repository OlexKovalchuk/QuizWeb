<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 06.09.2021
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/profile.scss" %>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/login.scss" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<div class="container animate">
    <center style="display: list-item;">
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <form method="get" action="${pageContext.request.contextPath}/profile/edit">
            <button type="submit" class="fill">
                <fmt:message key="msg.edit-personal-info"/>
            </button>
        </form>
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">

        <form method="get" action="${pageContext.request.contextPath}/profile/results?sort=date&page=1">
            <button type="submit" class="fill"><fmt:message key="msg.results"/></button>
        </form>
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <c:if test="${sessionScope.user.role.roleName.equals('admin')}">
                <button type="submit" onclick="location.href='${pageContext.request.contextPath}/profile/users?sort=score&page=1'" class="fill"><fmt:message key="msg.check-users"/></button>
        </c:if>
    </center>
</div>
</body>
</html>
