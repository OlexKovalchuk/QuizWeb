<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleksandr
  Date: 06.09.2021
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/user_table.scss" %>
        <%@include file="/frontend/css/style.css" %>
    </style>
</head>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<div class="caption">Users</div>
<div id="table" >
    <div class="header-row row">
        <span class="cell primary">login</span>
        <span class="cell">role</span>
        <span class="cell">create date</span>
        <span class="cell">result</span>
        <span class="cell">block</span>
    </div>
    <c:forEach items="${requestScope.users}" var="user">
        <c:if test="${user.id !=sessionScope.user.id}">
            <div class="row">
                <input type="radio" name="expand">
                <span class="cell primary" data-label="Vehicle">${user.login}</span>
                <span class="cell" data-label="Exterior">${user.role.roleName}</span>
                <span class="cell" data-label="Interior">${user.creationDate}</span>
                <span class="cell" data-label="Engine">${user.averageScore}</span>
                <span class="cell" data-label="Trans">
                    <a href="${pageContext.request.contextPath}/home?command=block&id=${user.id}&block=${user.block}">
                        <c:if test="${user.block==1}">un</c:if>
                       block<a></a>
            </span>
            </div>
        </c:if>
    </c:forEach>
</div>

</body>
</html>
