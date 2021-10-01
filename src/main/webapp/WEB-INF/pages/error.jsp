<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/error.scss" %>
        <%@include file="/frontend/css/style.css" %>
    </style>
</head>
<t:header >
<div class="container">
    <section class="page_404">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 ">
                    <div class="col-sm-10 col-sm-offset-1  text-center">
                        <div class="four_zero_four_bg">
                            <h1 class="text-center ">${requestScope.errorMessage.type}</h1>
                        </div>
                        <div class="contant_box_404">
                            <h3 class="h2">
                                ${requestScope.errorMessage.header}
                            </h3>
                            <p>${requestScope.errorMessage.description}</p>
                            <a href="${requestScope.errorMessage.redirectUrl}" class="link_404">${requestScope.errorMessage.buttonName}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</t:header>
</html>