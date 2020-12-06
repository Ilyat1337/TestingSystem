let questionPlaceholder;
let answerPlaceholder;
let addAnswerText;
let addQuestionText;
let deleteAnswerText;
let deleteQuestionText;

const lastAnswerNumbers = [0];
let questionsContainer;

document.addEventListener("DOMContentLoaded", () => {
    questionPlaceholder = document.querySelector("#question-0 input[name=question-0]").placeholder;
    answerPlaceholder = document.querySelector("#question-0 .answers input[name=answer-0-0]").placeholder;
    addAnswerText = document.querySelector("#question-0 #answer-0-0 input[type=button]").value;
    addQuestionText = document.querySelector("#question-0 .add-question-btn").value;
    deleteAnswerText = document.getElementById("delete-answer-text").innerText;
    deleteQuestionText = document.getElementById("delete-question-text").innerText;

    questionsContainer = document.getElementById("questions");
});

function addQuestion() {
    const newQuestionNumber = lastAnswerNumbers.length;
    lastAnswerNumbers.push(-1);
    const questionContainer = document.createElement("div");
    questionContainer.setAttribute("id", `question-${newQuestionNumber}`);
    questionContainer.setAttribute("class", "question-container");
    questionContainer.innerHTML =
        `
            <input type="text" required name="question-${newQuestionNumber}" class="question-text" placeholder="${questionPlaceholder}">
            <div class="answers">
            </div>
            <input type="button" onclick="removeQuestion(${newQuestionNumber})" value="${deleteQuestionText}">
        `;
    questionsContainer.appendChild(questionContainer);
    addAnswerField(newQuestionNumber);
    const answerButton = questionContainer.querySelector(".remove-answer-btn");
    answerButton.setAttribute("onclick", `addAnswerField(${newQuestionNumber})`);
    answerButton.setAttribute("value", addAnswerText);
    answerButton.removeAttribute("class");
    questionContainer.querySelector(".answer-radio").checked = true;
}

function removeQuestion(questionNumber) {
    const questionContainer = questionsContainer.querySelector(`#question-${questionNumber}`);
    if (!questionContainer) { return; }
    questionsContainer.removeChild(questionContainer);
}

function addAnswerField(questionNumber) {
    const answersContainer = questionsContainer.querySelector(`#question-${questionNumber} .answers`);
    if (!answersContainer) { return; }
    if (lastAnswerNumbers[questionNumber] === 6) { return; }
    const newAnswerNumber = ++lastAnswerNumbers[questionNumber];
    const answerContainer = document.createElement("div");
    const answerName = `answer-${questionNumber}-${newAnswerNumber}`;
    answerContainer.setAttribute("id", answerName);
    answerContainer.setAttribute("class", "answer");
    answerContainer.innerHTML =
        `
            <input type="radio" class="answer-radio" name="correct-answer-${questionNumber}" value="${newAnswerNumber}">
            <label class="question-number">${newAnswerNumber + 1}</label><input type="text" required name="${answerName}" class="answer-input" placeholder="${answerPlaceholder}">
            <input type="button" class="remove-answer-btn" onclick="removeAnswerField(${questionNumber}, ${newAnswerNumber})" value="${deleteAnswerText}">
        `;
    answersContainer.appendChild(answerContainer);
}

function removeAnswerField(questionNumber, answerNumber) {
    const answersContainer = questionsContainer.querySelector(`#question-${questionNumber} .answers`);
    if (!answersContainer) { return; }
    const answerContainer = answersContainer.querySelector(`#answer-${questionNumber}-${answerNumber}`);
    if (!answerContainer) { return; }
    if (answerContainer.querySelector(".answer-radio").checked) {
        answersContainer.querySelector(`input[name=correct-answer-${questionNumber}]`).checked = true;
    }
    answersContainer.removeChild(answerContainer);
    const lastAnswerNumber = --lastAnswerNumbers[questionNumber];
    const answerContainers = answersContainer.querySelectorAll(".answer");
    console.log(answerNumber, lastAnswerNumber);
    for (let i = answerNumber; i <= lastAnswerNumber; i++) {
        console.log(answerContainers[i]);
        changeAnswerContainerNumbers(answerContainers[i], questionNumber, i);
    }
}

function changeAnswerContainerNumbers(answerContainer, questionNumber, answerNumber) {
    const answerName = `answer-${questionNumber}-${answerNumber}`;
    answerContainer.setAttribute("id", answerName);
    answerContainer.querySelector(".question-number").innerText = answerNumber + 1;
    answerContainer.querySelector(".answer-radio").setAttribute("name", `correct-answer-${questionNumber}`);
    answerContainer.querySelector(".answer-radio").setAttribute("value", answerNumber);
    answerContainer.querySelector(".answer-input").setAttribute("name", answerName);
    answerContainer.querySelector(".remove-answer-btn").setAttribute("onclick", `removeAnswerField(${questionNumber}, ${answerNumber})`);
}