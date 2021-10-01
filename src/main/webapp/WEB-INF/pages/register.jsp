<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
<t:header >

    <form class="modal-content animate" action="${pageContext.request.contextPath}/register" method="post">
        <div class="container">
            <label><b><fmt:message key="msg.name"/></b></label>
            <label>
                <input type="text" title="<fmt:message key="msg.name-validation"/>" maxlength="32"
                       pattern="[A-Za-z\u0400-\u04ff]{1,32}" placeholder="<fmt:message key="msg.name-enter"/>"
                       name="name"
                       required>
            </label>
            <label><b><fmt:message key="msg.surname"/></b></label>
            <label>
                <input type="text" maxlength="32" title="<fmt:message key="msg.surname-validation"/>"
                       pattern="[A-Za-z\u0400-\u04ff]{1,32}" placeholder="<fmt:message key="msg.surname-enter"/>"
                       name="surname" required>
            </label>
            <label><b><fmt:message key="msg.email"/></b></label>
            <label>
                <input type="text" title="<fmt:message key="msg.email-validation"/> ivanov@gmail.com" maxlength="45"
                       pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                       placeholder="<fmt:message key="msg.email-enter"/>"
                       name="email"
                       required>
            </label>
            <label><b><fmt:message key="msg.password"/></b></label>
            <label>
                <input type="password" minlength="6" maxlength="45"
                       title="<fmt:message key="msg.password-validation"/>"
                       placeholder="<fmt:message key="msg.enter-password"/>"
                       pattern="[A-Za-z0-9 ]{1,32}"
                       name="password" required>
            </label>
            <button type="submit"><fmt:message key="msg.register"/></button>
        </div>
    </form>
</t:header>
</html>