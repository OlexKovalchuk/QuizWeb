<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<div class="header">
    <a href="#default" class="logo">Quiz</a>
    <div class="header-right">
        <c:if test="${sessionScope.lang==null}"> <a class="active"
                href="?lang=en&${pageContext.request.queryString}"><fmt:message key="msg.en"/></a>
            <a
               href="?lang=ua&${pageContext.request.queryString}"><fmt:message key="msg.ua"/></a>
        </c:if>
        <c:if test="${sessionScope.lang!=null}">
            <a  <c:if test="${sessionScope.lang=='ua'}"> class="active" </c:if>
               href="?lang=ua"><fmt:message key="msg.ua"/></a>
            <a <c:if test="${sessionScope.lang=='en'}"> class="active" </c:if>
                    href="?lang=en"><fmt:message key="msg.en"/></a>
        </c:if>

        <c:if test="${sessionScope.user!=null}"> <a
                href="${pageContext.request.contextPath}/logout"><fmt:message key="msg.logout"/></a></c:if>
        <c:if test="${empty sessionScope.user}"> <a href="${pageContext.request.contextPath}/login"><fmt:message key="msg.login"/></a>
            <a href="${pageContext.request.contextPath}/register"><fmt:message key="msg.register"/></a>
        </c:if>
        <a href="${pageContext.request.contextPath}/home"><fmt:message key="msg.quiz"/></a>
        <a href="${pageContext.request.contextPath}/profile"><fmt:message key="msg.profile"/></a>
    </div>
</div>