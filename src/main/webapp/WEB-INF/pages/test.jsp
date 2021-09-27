<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page buffer="none" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/test.scss" %>
        <%@include file="/frontend/css/profile.scss" %>
    </style>

</head>
<t:page title="Quiz">
    <div class="container" style="margin: 20px">
        <span id="remainingTime"
              style="position: fixed;top:90px;left: 80%;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;">
        </span>
        <form action="${pageContext.request.contextPath}/finish" method="post">
            <ol>
                <c:forEach items="${requestScope.quiz.questions}" var="question" varStatus="q">
                    <li>
                        <h3>${question.description}</h3>
                        <c:forEach items="${question.answers}" var="answer" varStatus="loop">
                            <div>
                                <input type="checkbox" name="question-${q.index}-answers"
                                       id="question-${q.index}-answers-${loop.index}}" value="${loop.index}"/>
                                <label for="question-${q.index}-answers-${loop.index}">${loop.index}) ${answer.description} </label>
                            </div>
                        </c:forEach>

                    </li>
                </c:forEach>
            </ol>
            <input type="hidden" name="id" value="${requestScope.quiz.id}">
            <input class="fill" type="submit" id="quiz"/>
        </form>
    </div>
    <script>
        var time =   ${requestScope.quiz.duration};
        time--;
        var sec = 59;
        document.getElementById("remainingTime").innerHTML = time + " : " + sec;
        //it calls fuction after specific time again and again
        var x = window.setInterval(timerFunction, 1000);

        function timerFunction() {
            sec--;
            // Display the result in the element with id="demo"


            if (time < 0) {
                clearInterval(x);
                document.getElementById("remainingTime").innerHTML = "00 : 00";
                document.getElementById("quiz").click();
            }
            document.getElementById("remainingTime").innerHTML = time + " : " + sec;
            if (sec == 0) {
                sec = 60;
                time--;

            }
        }
    </script>
</t:page>
</html>