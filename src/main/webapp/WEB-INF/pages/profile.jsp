<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
<t:page title="Profile">
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
            <button type="submit"
                    onclick="location.href='${pageContext.request.contextPath}/profile/users?sort=score&page=1'"
                    class="fill"><fmt:message key="msg.check-users"/></button>
        </c:if>
    </center>
</div>
</t:page>
</html>