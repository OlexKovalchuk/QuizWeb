<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<<!DOCTYPE html>
<html lang="java">

<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        <%@ include file="/frontend/css/login.scss" %>
        <%@ include file="/frontend/css/style.css" %>
    </style>
</head>

<t:page title="Your info">
    <form class="modal-content animate" action="${pageContext.request.contextPath}/user/edit" method="post">
        <div class="container">
            <label><b><fmt:message key="msg.name"/></b></label>
            <label>
                <input type="text" title="<fmt:message key="msg.name-validation"/>" maxlength="32"
                       pattern="[A-Za-z\u0400-\u04ff]{1,32}" value="${requestScope.user.name}" placeholder="<fmt:message key="msg.name-enter"/>"
                       name="name"
                       required>
            </label>
            <label><b><fmt:message key="msg.surname"/></b></label>
            <label>
                <input type="text" maxlength="32" title="<fmt:message key="msg.surname-validation"/>"
                       pattern="[A-Za-z\u0400-\u04ff]{1,32}" value="${requestScope.user.surname}" placeholder="<fmt:message key="msg.surname-enter"/>"
                       name="surname" required>
            </label>
            <label><b><fmt:message key="msg.email"/></b></label>
            <label>
                <p style="  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;">${requestScope.user.email}</p>
            </label>
            <label><b><fmt:message key="msg.role"/></b></label>
            <select name="role">
                <option value="student" <c:if test="${requestScope.user.role.roleName.equals('student')}">selected</c:if> ><fmt:message key="msg.student"/></option>
                <option value="admin" <c:if test="${requestScope.user.role.roleName.equals('admin')}">selected</c:if>><fmt:message key="msg.admin"/></option>
            </select>
            <input type="hidden" name="id" value="${requestScope.user.id}">
            <button type="submit"><fmt:message key="msg.edit"/></button>
        </div>

    </form>
    <form class="modal-content animate"  action="${pageContext.request.contextPath}/user/delete" method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <button style="background-color: red" type="submit"><fmt:message key="msg.user-delete"/></button>
    </form>

</t:page>
</html>