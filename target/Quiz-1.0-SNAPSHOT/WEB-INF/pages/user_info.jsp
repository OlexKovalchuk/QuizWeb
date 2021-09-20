<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE html>
<html lang="java">

<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        <%@ include file="/frontend/css/login.scss" %>
        <%@ include file="/frontend/css/style.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<form class="modal-content animate" action="${pageContext.request.contextPath}/profile/edit" method="post">
    <div class="container">
        <label><b><fmt:message key="msg.name"/></b></label>

        <label>
            <input type="text" maxlength="32" title="<fmt:message key="msg.name-validation"/>"
                   pattern="[A-Za-z\u0400-\u04ff]{1,32}" placeholder="${sessionScope.user.name}" name="name">
        </label>
        <label><b><fmt:message key="msg.surname"/></b></label>
        <label>
            <input type="text" maxlength="32" title="<fmt:message key="msg.surname-validation"/>"
                   pattern="[A-Za-z\u0400-\u04ff]{1,32}" placeholder="${sessionScope.user.surname}" name="surname">
        </label>
        <label><b><fmt:message key="msg.email"/></b></label>

        <label>
            <input type="text" title="<fmt:message key="msg.email-validation"/> ivanov@gmail.com" maxlength="45"
                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="${sessionScope.user.email}"
                   name="email">
        </label>
        <label><b><fmt:message key="msg.password"/></b></label>

        <label>
            <input type="password" minlength="6" title="<fmt:message key="msg.password-validation"/>"
                   maxlength="45" pattern="[A-Za-z0-9 ]{1,32}" placeholder="***********" name="password">
        </label>
        <button type="submit"><fmt:message key="msg.edit"/></button>
    </div>
</form>
</body>
</html>