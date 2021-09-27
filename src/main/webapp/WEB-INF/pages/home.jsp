<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
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
        <%@include file="/frontend/css/profile.scss" %>
        <%@include file="/frontend/css/pagination.scss" %>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<t:page title="Home">
    <c:if test="${sessionScope.user!=null && sessionScope.user.role.roleName.equals('admin')}">
        <form action="${pageContext.request.contextPath}/create" method="get">
            <button style="right:5%;top:12.5%; width:10%; position:absolute;" type="submit">
                <span class="jss1104"><fmt:message key="msg.quiz-add"/></span><span class="jss539"></span></button>

        </form>
    </c:if>
    <form action="${pageContext.request.contextPath}/home" method="get">
        <label for="plan" href=""><fmt:message
                key="msg.sort-by"/></label><select name="sort" id="plan">
        <option value="question" <c:if test="${requestScope.sort.equals('question')}">selected</c:if>><fmt:message
                key="msg.questions"/></option>
        <option value="duration" <c:if test="${requestScope.sort.equals('duration')}">selected</c:if>><fmt:message
                key="msg.duration"/></option>
        <option value="difficult" <c:if test="${requestScope.sort.equals('difficult')}">selected</c:if>><fmt:message
                key="msg.difficult"/></option>
        <option value="date" <c:if test="${requestScope.sort.equals('date')}">selected</c:if>><fmt:message
                key="msg.date"/></option>
        <option value="topic" <c:if test="${requestScope.sort.equals('topic')}">selected</c:if>><fmt:message
                key="msg.topic"/></option>
    </select>
        <label for="topic" href=""><fmt:message
                key="msg.topic"/></label>
        <select name="topic" id="topic">
            <option value="all" <c:if test="${requestScope.topic.equals('all')}">selected</c:if>><fmt:message
                    key="msg.all"/></option>
            <c:forEach items="${requestScope.topics}" var="topic">
                <option value="${topic.id}"
                        <c:if test="${not requestScope.topic.equals('all') && requestScope.topic==topic.id}">selected</c:if>>${topic.name} </option>
            </c:forEach>
        </select>
        <button type="submit" style="left: 8.8%;
    top: 12.1%;
    width: 7.3%;
    height: 1.9%;
    position: initial;"><fmt:message key="msg.sort"/>
        </button>
    </form>
    <div class="container animate">
        <div class="row">
            <c:forEach var="quiz" items="${requestScope.quizzes}">
                <div class="col-md-3 col-sm-6">
                    <div class="serviceBox">
                        <c:if test="${sessionScope.user.role.roleName.equals('admin')}">
                            <form action="${pageContext.request.contextPath}/delete" method="post">
                                <input type="hidden" name="id" value="${quiz.id}">
                                <button style="background:url(https://www.uidownload.com/files/240/295/614/delete-icon.jpg); position: absolute;height:20%; width:20%;top:0;right: 0;"
                                        type="submit"><img
                                        src="https://www.uidownload.com/files/240/295/614/delete-icon.jpg" width="50px"
                                        height="50px"></button>
                            </form>
                        </c:if>
                        <div class="service-content">
                            <img src="https://clf1.medpagetoday.net/media/images/94xxx/94357.jpg" height="150px"
                                 width="200px"
                                 alt="img"
                                 style="border-radius: 60%;"/>
                            <h3 class="title">${quiz.header} </h3>
                            <p class="description">${quiz.description}</p>
                        </div>
                    </div>
                    <div class="jss87 jss106 jss944">
                        <div
                                <c:if test="${quiz.difficult.equals('easy')}">style="color:green"</c:if>
                                <c:if test="${quiz.difficult.equals('medium')}">style="color:orange"</c:if>
                                <c:if test="${quiz.difficult.equals('hard')}">style="color:red"</c:if>
                                class="jss88 jss938 displayOutHover">
                            <c:if test="${quiz.difficult.equals('easy')}"><fmt:message key="msg.difficult-easy"/></c:if>
                            <c:if test="${quiz.difficult.equals('medium')}"><fmt:message
                                    key="msg.difficult-medium"/></c:if>
                            <c:if test="${quiz.difficult.equals('hard')}"><fmt:message key="msg.difficult-hard"/></c:if>
                        </div>
                    </div>
                    <div class="jss87 jss106 jss944">
                        <div style="line-height: 0.428571;font-size: 20px;" class="jss88 jss938 displayOutHover">
                            <fmt:message key="msg.questions"/>: ${quiz.count}
                            <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
                            <fmt:message key="msg.duration"/>: ${quiz.duration}
                        </div>
                    </div>
                    <div class="jss968">
                        <div class="jss87 jss956">
                            <c:if test="${sessionScope.user.role.roleName.equals('admin')}">
                                <div class="jss88 jss121" style="padding-left: 8px">
                                    <form action="${pageContext.request.contextPath}/edit" method="get">
                                        <button class="jss370 jss1103 jss1105 jss1108 jss949 jss951" tabindex="0"
                                                type="submit" name="id" value="${quiz.id}">
                                            <span class="jss1104"><fmt:message key="msg.edit"/></span><span
                                                class="jss539"></span></button>
                                    </form>
                                </div>
                            </c:if>
                            <div class="jss88 jss121" style="padding-right: 8px">
                                <form action="${pageContext.request.contextPath}/start" method="post">
                                    <button class="jss370 jss1103 jss1105 jss1108 jss949 jss950" tabindex="0"
                                            type="submit"
                                            name="id" value="${quiz.id}">
                                        <span class="jss1104"><fmt:message key="msg.start"/></span><span
                                            class="jss539"></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
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
            return '${pageContext.request.contextPath}/home?sort=' + sort.value + '&page=' + page;
        }
    </script>
</t:page>
</html>