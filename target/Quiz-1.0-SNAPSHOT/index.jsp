<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE html>
<html lang="java">

<head>
    <meta charset="utf-8">
    <title>Quiz</title>
    <style>
        <%@ include file="/frontend/css/login.scss" %>
        <%@ include file="/frontend/css/style.css" %>
    </style>
</head>
<body>
<%@include file="WEB-INF/pages/template/header.jspf" %>
<form class="modal-content animate" action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <label><b><fmt:message key="msg.email"/></b></label>
        <label>
            <input type="text" title="<fmt:message key="msg.email-validation"/> ivanov@gmail.com" maxlength="45"
                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                   placeholder="<fmt:message key="msg.email-enter"/>" name="email" required>
        </label>
        <label><b><fmt:message key="msg.password"/></b></label>
        <label>
            <input type="password" minlength="6" pattern="[A-Za-z0-9 ]{1,32}"
                   title="<fmt:message key="msg.password-validation"/>" maxlength="45"
                   placeholder="<fmt:message key="msg.enter-password"/>" name="password" required>
        </label>
        <button type="submit"><fmt:message key="msg.login"/></button>
        <c:if test="${requestScope.errorMessage!=null}">
            <label><b style="color:red">${requestScope.errorMessage}</b></label>
        </c:if>
        <span class="psw"><fmt:message key="msg.dont-have-acc"/>
            <a
                    href="${pageContext.request.contextPath}/register"><fmt:message key="msg.register"/></a></span>
    </div>
</form>
</body>
</html>