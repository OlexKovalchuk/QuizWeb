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
            <input type="text" placeholder="Enter name" name="name" required>
        </label>
        <label><b>Surname</b></label>
        <label>
            <input type="text" placeholder="Enter Surname" name="surname" required>
        </label>
        <label><b>Login</b></label>
        <label>
            <input type="text" placeholder="Enter Login" name="login" required>
        </label>
        <label><b>Password</b></label>
        <label>
        <input type="password" placeholder="Enter Password" name="password" required>
    </label>
        <button type="submit" name="command" value="register" >Register</button>
    </div>
</form>
<script>
    <%@include file="/frontend/js/login.js" %>
</script>
</body>
</html>