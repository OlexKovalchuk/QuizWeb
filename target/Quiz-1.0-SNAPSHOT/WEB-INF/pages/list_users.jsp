<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Profile</title>
    <style>
        <%@include file="/frontend/css/user_table.scss" %>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/pagination.scss" %>
    </style>
</head>
<t:header >
    <div class="container">
        <form action="${pageContext.request.contextPath}/profile/users" method="get">
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
                    <c:if test="${requestScope.sort.equals('block')}">selected</c:if> value="block"><fmt:message
                    key="msg.block"/>
            </option>
        </select>
            <button type="submit" style="left: 8.8%;
    top: 12.1%;
    width: 4.3%;
    height: 1.9%;
    position: initial;"><fmt:message key="msg.sort"/>
            </button>
            <div class="caption"><fmt:message key="msg.users"/></div>
            <div id="table">
                <div class="header-row row">
                    <span class="cell primary"><fmt:message key="msg.email"/></span>
                    <span class="cell"><fmt:message key="msg.role"/></span>
                    <span class="cell"><fmt:message key="msg.date"/></span>
                    <span class="cell"><fmt:message key="msg.results"/></span>
                    <span class="cell"><fmt:message key="msg.block"/></span>
                </div>
                <c:forEach items="${requestScope.users}" var="user">
                    <div class="row">
                        <input type="radio" name="expand">
                      <a  href="${pageContext.request.contextPath}/user/edit?id=${user.id}"> <span class="cell primary" data-label="Vehicle">${user.email}</span></a>
                        <span class="cell" data-label="Exterior">${user.role.roleName}</span>
                        <span class="cell" data-label="Interior">${user.creationDate}</span>
                        <span class="cell" data-label="Engine">${user.averageScore}</span>
                        <span class="cell" data-label="Trans">
                      <button type="submit" name="id" value="${user.id}" style="  background: none!important;
  border: none;
  padding: 0!important;
  /*optional*/
  font-family: arial, sans-serif;
  /*input has OS specific font-family*/
  color: #069;
  text-decoration: underline;
  cursor: pointer;">

                        <c:if test="${user.block==0}"><fmt:message key="msg.block-user"/></c:if>
                          <c:if test="${user.block==1}"><fmt:message key="msg.unblock-user"/></c:if></button>

            </span>
                    </div>
                </c:forEach>
            </div>

            <div class="center">
                <div class="pagination">
                    <a href="#">&laquo;</a>
                    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                        <a <c:if test="${requestScope.page==i}">class="active"</c:if>
                                onclick="location.href=getLink(${i});">${i}</a>
                    </c:forEach>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </form>
    </div>
    <script>
        function getLink(page) {
            var sort = document.getElementById('plan');
            return '${pageContext.request.contextPath}/profile/users?sort=' + sort.value + '&page=' + page;
        }
    </script>
</t:header>
</html>