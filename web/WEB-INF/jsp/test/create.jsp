<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    .question-container {
        margin: 15px 0;
    }
</style>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/TestContainers.js"></script>
    <div id="delete-answer-text" style="display: none">Delete answer</div>
    <div id="delete-question-text" style="display: none">Delete question</div>
    <form method="post" action="${pageContext.request.contextPath}/test/save">
        <input type="text" required name="test-name" placeholder="Test name">
        <div id="questions">
            <div id="question-0" class="question-container">
                <input type="text" name="question-0" class="question-text" placeholder="Question">
                <div class="answers">
                    <div id="answer-0-0" class="answer">
                        <input type="radio" class="answer-radio" checked name="correct-answer-0" value="0">
                        <label class="question-number">1</label><input type="text" required name="answer-0-0" class="answer-input" placeholder="Answer">
                        <input type="button" onclick="addAnswerField(0)" value="Add answer">
                    </div>
                </div>
                <input type="button" class="add-question-btn" onclick="addQuestion()" value="Add question">
            </div>
        </div>
        <input type="submit" value="Save">
    </form>
</body>
</html>
