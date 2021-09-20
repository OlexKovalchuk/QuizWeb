<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/table.scss" %>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/login.scss" %>
        <%@include file="/frontend/css/profile.scss" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<div class="container animate">
    <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">${requestScope.result.topicName}</div>
            <div class="col col-2">${requestScope.result.testHeader}</div>
            <div class="col col-3">${requestScope.result.startDate}
            </div>
            <div class="col col-4">${requestScope.result.completeDate}
            </div>
            <div class="col col-5">${requestScope.result.score}
            </div>
        </li>
    </ul>
    <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
    <center>
        <button type="submit" class="fill"
                onclick="location.href='${pageContext.request.contextPath}/home'">Back to quizzes
        </button>
    </center>
</div>
</body>
</html>