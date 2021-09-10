<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<form class="modal-content animate" action="${pageContext.request.contextPath}/home" method="post">
    <div class="container">
        <label><b>Name</b></label>
        <label>
            <input type="text" placeholder="${sessionScope.user.firstName}" name="name" >
        </label>
        <label><b>Surname</b></label>
        <label>
            <input type="text" placeholder="${sessionScope.user.secondName}" name="surname" >
        </label>
        <label><b>Login</b></label>
        <label>
            <input type="text" placeholder="${sessionScope.user.login}" name="login" >
        </label>
        <label><b>Password</b></label>
        <label>
            <input type="password" placeholder="***********" name="password" >
        </label>
        <button type="submit" name="command" value="editUserPersonalInfo" >Edit</button>
    </div>
</form>
<script>
    <%@include file="/frontend/js/login.js" %>
</script>
</body>
</html>