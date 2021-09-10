<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page buffer="none" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/frontend/css/style.css" %>
        <%@include file="/frontend/css/test.scss" %>
        <%@include file="/frontend/css/profile.scss" %>
    </style>

</head>
<body>

<div class="header">
    <a href="#default" class="logo">Quiz</a>
</div>

<div class="container">
        <span id="remainingTime"
              style="position: fixed;top:90px;left: 300px;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;">
        </span>
    <form action="${pageContext.request.contextPath}/home" method="get">
        <ol>
            <c:forEach items="${requestScope.test.questions}" var="question" varStatus="q">
                <li>
                    <h3>${question.description}</h3>
                        <c:forEach items="${question.answers}" var="answer" varStatus="loop">
                            <div>
                                <input type="radio" name="question-${q.index}-answers" id="question-${q.index}-answers-${loop.index}}" value="${loop.index}" />
                                <label for="question-${q.index}-answers-${loop.index}">${loop.index}) ${answer.description} </label>
                            </div>
                        </c:forEach>

                </li>
            </c:forEach>
        </ol>
        <input type="hidden" name="id" value="${requestScope.test.id}">
          <input class="fill" type="submit" name="command"  value="finishQuiz" id="quiz" />
    </form>
</div>
<script>
    <%--var time = ${requestScope.test.duration};--%>
    var time =1;
    time--;
    var sec = 60;
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
</body>
</html>
