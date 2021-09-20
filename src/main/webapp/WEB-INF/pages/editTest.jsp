<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE html>
<html lang="java">

<head>
    <meta charset="UTF-8">
    <title>Test-Create</title>
    <style>
        <%@ include file="/frontend/css/login.scss" %>
        <%@ include file="/frontend/css/style.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/pages/template/header.jspf" %>
<div class="container">
    <form class="modal-content animate" id="editForm" onsubmit="return handleData();"
          action="${pageContext.request.contextPath}/edit" method="post">
        <input type="hidden" name="quizId" value="${requestScope.quiz.id}">
        <label><b><fmt:message key="msg.header"/></b></label>
        <label>
            <input type="text" title="<fmt:message key="msg.header-validation"/>"
                   placeholder="<fmt:message key="msg.header-enter"/>" minlength="5" maxlength="39" name="header"
                   value="${requestScope.quiz.header}" required>
        </label>

        <label><b><fmt:message key="msg.topic"/></b></label>
        <label>
            <select name="topic">
                <c:forEach items="${requestScope.topics}" var="topic">
                    <option value="${topic.id}"
                            <c:if test="${requestScope.quiz.topicId==topic.id}">selected</c:if> >${topic.name}</option>
                </c:forEach>
            </select>
        </label>  <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <label><b><fmt:message key="msg.difficult"/></b></label>
        <label>
            <select name="difficult" required>
                <option <c:if
                        test="${requestScope.quiz.difficult.equals('easy')}"> select="selected"</c:if>style="color: green"
                        value="easy"><fmt:message key="msg.difficult-easy"/>
                </option>
                <option <c:if
                        test="${requestScope.quiz.difficult.equals('medium')}"> select="selected"</c:if>style="color: orange"
                        value="medium"><fmt:message key="msg.difficult-medium"/>
                </option>
                <option <c:if test="${requestScope.quiz.difficult.equals('hard')}">select="selected"
                        </c:if>style="color: red"
                        value="hard"><fmt:message key="msg.difficult-hard"/>
                </option>
            </select>
        </label>
        <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
        <label><b><fmt:message key="msg.duration"/></b></label>
        <label>
            <input type="text" title="<fmt:message key="msg.duration-validate"/>" pattern="\d{1,3}"
                   placeholder="<fmt:message key="msg.duration-enter"/>" name="duration" value="${requestScope.quiz.duration}"/>
        </label>
        <label><b><fmt:message key="msg.description"/></b></label>
        <label>
                <textarea placeholder="<fmt:message key="msg.description-enter"/>" rows="4" cols="50" maxlength="499"
                          form="editForm" name="description">${requestScope.quiz.description}
                </textarea>
        </label>
        <ol id="questions">
            <c:set var="countAnswer" value="0" scope="page"/>
            <c:set var="countQuestion" value="0" scope="page"/>
            <c:forEach items="${requestScope.quiz.questions}" var="question">
                <li id="question${countQuestion}"><input type="button" onclick="deleteQuestion(${countQuestion})"
                                                         value="<fmt:message key="msg.question-delete"/>"/>
                    <h3><fmt:message key="msg.description"/></h3>
                    <input type="text" name="description${countQuestion}"
                           title="<fmt:message key="msg.question-validate"/>" maxlength="400"
                           placeholder="<fmt:message key="msg.question-enter"/>"
                           value="${question.description}" required/>
                    <input type="button" onclick="createAnswer(${countQuestion})"
                           value="<fmt:message key="msg.answer-add"/>"/>
                    <div style="visibility:hidden; color:red; " id="chk_option_error${countQuestion}">
                        <fmt:message key="msg.checkbox-validate"/>
                    </div>
                    <c:forEach items="${question.answers}" var="answer">
                        <div id="question${countQuestion}-answer-${countAnswer}"><input type="button"
                                                                                        onclick="deleteAnswer(${countQuestion},${countAnswer})"
                                                                                        value="<fmt:message key="msg.answer-delete"/>"/>
                            <hr style=" clear: both;
    border: 1px solid transparent;
    height: 0px;">
                            <input type="checkbox" name="question-${countQuestion}-answers"
                                   id="question-${countQuestion}-answers-${countAnswer}"
                                   <c:if test="${answer.answer==1}">checked</c:if> value="${countAnswer}"/>
                            <input type="text" title="<fmt:message key="msg.answer-validate"/>" maxlength="100"
                                   name="answer-${countAnswer}" value="${answer.description}"
                                   placeholder="<fmt:message key="msg.answer-enter"/>" required/>
                            <c:set var="countAnswer" value="${countAnswer + 1}" scope="page"/>
                        </div>
                    </c:forEach>
                    <c:set var="countQuestion" value="${countQuestion + 1}" scope="page"/>
                </li>
            </c:forEach>
        </ol>

        <input type="button" onclick="createQuestion();" value="<fmt:message key="msg.question-add"/>"/>
        <center>
            <button type="submit"><fmt:message key="msg.quiz-save"/></button>
        </center>
    </form>


</div>

<script>
    var array = new Array();
    var qId = ${countQuestion};
    var aId = ${countAnswer};
    for (i = 0; i < qId; i++) {
        array.push(i);
    }

    function createQuestion() {
        qId++;
        let questions = document.getElementById('questions');
        const question = document.createElement('li');
        question.id = 'question' + qId;
        question.innerHTML = '<input type="button" onclick="deleteQuestion(' + qId + ')" value="<fmt:message key="msg.question-delete"/>"/>' +
            '<h3><fmt:message key="msg.description"/></h3><input type="text" title="<fmt:message key="msg.question-validate"/>" maxlength="400" name="description' + qId +
            '" placeholder="<fmt:message key="msg.question-enter"/>" required/> <input type="button" onclick="createAnswer(' + qId + ')" value="<fmt:message key="msg.answer-add"/>"/>    <div style="visibility:hidden; color:red; " id="chk_option_error' + qId + '">' +
            ' <fmt:message key="msg.checkbox-validate"/></div>';
        questions.appendChild(question);
        array.push(qId);
        createAnswer(qId);

    }

    function createAnswer(id) {
        aId++;
        const question = document.getElementById('question' + id);
        const answer = document.createElement('div');
        answer.id = question.id + '-answer-' + aId;
        answer.innerHTML = '<input type="button" onclick="deleteAnswer(' + id + ',' + aId + ')" value="<fmt:message key="msg.answer-delete"/>"/>   <hr style=" clear: both;' +
            ' border: 1px solid transparent;  height: 0px;"> <input type="checkbox" name="question-' + question.id.substr(8) + '-answers" id="question-' + question.id.substr(8) + '-answers-' + aId + '" value="' + aId + '"/>' +
            ' <input type = "text" title="<fmt:message key="msg.answer-validate"/>" maxlength="100" name="answer-' + aId + '" placeholder="<fmt:message key="msg.answer-add"/>" required />';
        question.appendChild(answer);
        const answerArray = questionMap.get(parseInt(question.id.substr(8)));
        answerArray.push(aId);
    }

    function deleteQuestion(id) {
        document.getElementById('question' + id).remove();
        removeA(array, id);
    }

    function removeA(arr) {
        var what, a = arguments, L = a.length, ax;
        while (L > 1 && arr.length) {
            what = a[--L];
            while ((ax = arr.indexOf(what)) !== -1) {
                arr.splice(ax, 1);
            }
        }
        return arr;
    }

    function deleteAnswer(id, id2) {
        document.getElementById('question' + id + "-answer-" + id2).remove();

    }

    function getAnswerCount() {
        return aId;
    }

    function handleData() {
        var form_data = new FormData(document.querySelector('form'));
        for (const value of array) {
            if (!form_data.has('question-' + value + '-answers')) {
                document.getElementById("chk_option_error" + value).style.visibility = "visible";
                return false
            }
            document.getElementById("chk_option_error" + value).style.visibility = "hidden";
        }
        return true;
    }
</script>
</body>
</html>
