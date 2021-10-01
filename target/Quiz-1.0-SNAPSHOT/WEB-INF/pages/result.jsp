<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
        <%@include file="/frontend/css/pagination.scss" %>
    </style>
</head>
<t:header >
    <form action="${pageContext.request.contextPath}/profile/results" method="get">
        <label for="plan" href="">Sort by</label><select name="sort" id="plan">
        <option
                <c:if test="${requestScope.sort.equals('date')}">selected</c:if> value="date"><fmt:message
                key="msg.date"/>
        </option>
        <option
                <c:if test="${requestScope.sort.equals('score')}">selected</c:if> value="score"><fmt:message
                key="msg.score"/>
        </option>
        <option
                <c:if test="${requestScope.sort.equals('block')}">selected</c:if> value="topic"><fmt:message
                key="msg.topic"/>
        </option>
    </select>
        <button type="submit" style="left: 8.8%;
    top: 12.1%;
    width: 4.3%;
    height: 1.9%;
    position: initial;">Sort
        </button>
    </form>
    <div class="container animate">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col col-1"><fmt:message key="msg.topic-name"/></div>
                <div class="col col-2"><fmt:message key="msg.quiz-name"/></div>
                <div class="col col-3"><fmt:message key="msg.date-start"/>
                </div>
                <div class="col col-4"><fmt:message key="msg.date-end"/>
                </div>
                <div class="col col-5"><fmt:message key="msg.score"/>
                </div>
            </li>
            <c:forEach items="${requestScope.userResults}" var="result">
                <li class="table-row">
                    <div class="colbefore col-1" data-label="Job Id">${result.topicName}</div>
                    <div class="colbefore col-2" data-label="Customer Name">${result.testHeader}</div>
                    <div class="colbefore col-3" data-label="Amount"><fmt:formatDate type = "both" pattern ="dd/MM/yyyy HH:mm"  value = "${result.startDate}" /></div>
                    <div class="colbefore col-4" data-label="Payment Status"><fmt:formatDate type = "both" pattern ="dd/MM/yyyy HH:mm"  value = "${result.completeDate}" /></div>
                    <div class="colbefore col-5" data-label="Payment Status">${result.score}</div>
                </li>
            </c:forEach>
        </ul>
        <div class="center">
            <div class="pagination">
                <a href="#">&laquo;</a>
                <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                    <a
                            <c:if test="${requestScope.page==i}">class="active"</c:if>
                            onclick="location.href=getLink(${i});">${i}</a>
                </c:forEach>
                <a href="#">&raquo;</a>
            </div>
        </div>

    </div>
    <script>
        function getLink(page) {
            var sort = document.getElementById('plan');
            return '${pageContext.request.contextPath}/profile/results?sort=' + sort.value + '&page=' + page;
        }
    </script>
</t:header>
</html>