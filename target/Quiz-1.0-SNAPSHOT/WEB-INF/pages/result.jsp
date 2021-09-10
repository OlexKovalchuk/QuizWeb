<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/table.scss" %>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/login.scss" %>
        <%@include file="/frontend/css/profile.scss" %>
        .pagination {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
        }
        .pagination form button {
            height: 25px;
            border: 1px solid black;
        }
        .pagination form button:hover {
            cursor: pointer;
            opacity: 50%;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<div class="container animate" >
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">topic name</div>
            <div class="col col-2">test name</div>
            <div class="col col-3">start
                date
            </div>
            <div class="col col-4">finish
                date
            </div>
            <div class="col col-5">score
            </div>
        </li>
        <c:forEach items="${requestScope.userResults}" var="result">
            <li class="table-row">
                <div class="colbefore col-1" data-label="Job Id">${result.topicName}</div>
                <div class="colbefore col-2" data-label="Customer Name">${result.testHeader}</div>
                <div class="colbefore col-3" data-label="Amount">${result.startDate}</div>
                <div class="colbefore col-4" data-label="Payment Status">${result.completeDate}</div>
                <div class="colbefore col-5" data-label="Payment Status">${result.score}</div>
            </li>
        </c:forEach>
    </ul>
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${requestScope.pagesAmount}">
            <form action="${pageContext.request.contextPath}/home" method="GET">
                <button class="fill" type="submit" name="command" value=result><c:out value="${i}"/></button>
                <input type="hidden" name="page" value=${i}>
            </form>
        </c:forEach>

    </div>
</div>
</body>
</html>
