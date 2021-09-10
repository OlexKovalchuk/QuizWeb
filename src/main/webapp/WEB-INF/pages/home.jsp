<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="java">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Quiz</title>
    <style>
        <%@ include file="/frontend/css/style.css" %>
        <%@ include file="/frontend/css/list.scss" %>
        <%@include file="/frontend/css/login.scss" %>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<%@ include file="/WEB-INF/pages/template/header.jspf" %>
<div class="container animate">
    <div class="row">
        <c:forEach var="test" items="${requestScope.tests}">
            <div class="col-md-3 col-sm-6">
                <div class="serviceBox">
                    <div class="service-content">
                        <img src="frontend/user.png" height="150px" width="150px" alt="img"
                             style="border-radius: 60%;"/>
                        <h3 class="title">${test.header} </h3>
                        <p class="description">${test.description}</p>
                    </div>
                </div>

                <div class="jss87 jss106 jss944">
                    <div class="jss88 jss938 displayOutHover">
                            ${test.difficult}
                    </div>
                    <div class="jss88 jss940 displayOutHover">
                <span class="jss941">
                        Questions :
                    </span>
                            ${test.count}
                    </div>
                </div>
                <div class="jss968">
                    <div class="jss87 jss956">
                        <c:if test="${sessionScope.user.role.roleName.equals('teacher')}">
                            <div class="jss88 jss121" style="padding-left: 8px">
                                <button class="jss370 jss1103 jss1105 jss1108 jss949 jss951" tabindex="0" type="button" href="${pageContext.request.contextPath}/home?command=editTest&id=${test.id}">
                                    <span class="jss1104">Редактировать</span><span class="jss539"></span></button>
                            </div>
                        </c:if>
                        <div class="jss88 jss121" style="padding-right: 8px">
                            <button class="jss370 jss1103 jss1105 jss1108 jss949 jss950" tabindex="0" type="button" onclick="location.href='${pageContext.request.contextPath}/home?command=startTest&id=${test.id}'">
                                <span class="jss1104">Начать</span><span class="jss539"></span></button>
                        </div>

                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
