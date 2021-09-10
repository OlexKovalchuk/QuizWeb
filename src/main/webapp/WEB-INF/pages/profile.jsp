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
        <button type="button" class="fill"
                onclick="location.href='${pageContext.request.contextPath}/home?command=loadEditPersonalInfo'">Edit your
            personal info
        </button>
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <button type="button" class="fill"
                onclick="location.href='${pageContext.request.contextPath}/home?command=result'">Results
        </button>
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <c:if test="${sessionScope.user.role.roleName.equals('teacher')}">
            <button type="submit" class="fill"
                    onclick="location.href='${pageContext.request.contextPath}/home?command=listUsers'">Check users
            </button>
        </c:if>
    </center>
</div>
</body>
</html>
