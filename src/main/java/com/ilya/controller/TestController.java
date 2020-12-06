package com.ilya.controller;

import com.ilya.dto.TestWithQuestions;
import com.ilya.entity.Answer;
import com.ilya.entity.Question;
import com.ilya.entity.Test;
import com.ilya.service.ServiceException;
import com.ilya.service.ServiceFactory;
import com.ilya.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class TestController extends HttpServlet {
    private static final String RELATIVE_CONTROLLER_PATH = "test";

    private static final String TEST_JSP_PATH = "/WEB-INF/jsp/test/test.jsp";
    private static final String CREATE_JSP_PATH = "/WEB-INF/jsp/test/create.jsp";

    private static final String LIST_RELATIVE_PATH = "list";
    private static final String CREATE_RELATIVE_PATH = "create";
    private static final String SAVE_RELATIVE_PATH = "save";

    private static final String QUESTION_PARAM_START = "question-";
    private static final String ANSWER_PARAM_FORMAT = "answer-%d-%d";

    private final Map<String, RequestHandler> requestHandlers;

    public TestController() {
        requestHandlers = new HashMap<>();
        requestHandlers.put(LIST_RELATIVE_PATH, this::showTestList);
        requestHandlers.put(CREATE_RELATIVE_PATH, this::createTest);
        requestHandlers.put(SAVE_RELATIVE_PATH, this::saveTest);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String idParam = request.getParameter("id");
        //request.setAttribute("id", idParam);
        commonGetPostHandler(request, response);
//        Dao<User> userDao = DaoFactory.getInstance().getUserDao();
//        String requestUri = request.getRequestURI();
//        try {
//            List<User> users = userDao.getAll();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        log.info("Get in test!");
//        request.getRequestDispatcher(TEST_JSP_PATH).forward(request, response);


        //response.sendRedirect("index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commonGetPostHandler(request, response);
    }

    private void commonGetPostHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String relativePath = UriUtils.tryGetRelativeUri(request.getRequestURI(), request.getContextPath() + "/" + RELATIVE_CONTROLLER_PATH);
        if (requestHandlers.containsKey(relativePath)) {
            requestHandlers.get(relativePath).handleRequest(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showTestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(TEST_JSP_PATH).forward(request, response);
    }

    private void createTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CREATE_JSP_PATH).forward(request, response);
    }

    private void saveTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getMethod().equals("POST")) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        List<Question> questions = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            if (parameterName.startsWith(QUESTION_PARAM_START)) {
                int questionNumber = Integer.parseInt(parameterName.substring(QUESTION_PARAM_START.length()));
                List<Answer> answers = new ArrayList<>();
                int currAnswer = 0;
                String answerParam = String.format(ANSWER_PARAM_FORMAT, questionNumber, currAnswer);
                String answerText;
                while ((answerText = request.getParameter(answerParam)) != null) {
                    Answer answer = new Answer();
                    answer.setText(answerText);
                    answers.add(answer);
                    answerParam = String.format(ANSWER_PARAM_FORMAT, questionNumber, ++currAnswer);
                }
                Question question = new Question();
                question.setText(request.getParameter(parameterName));
                question.setCorrectAnswer(Integer.parseInt(request.getParameter("correct-answer-" + questionNumber)));
                question.setAnswers(answers);
                questions.add(question);
            }
        }
        Test test = new Test();
        test.setName(request.getParameter("test-name"));
        test.setQuestionCount(questions.size());

//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        test.setCreatorId(user.getId());
        test.setCreatorId(7);

        TestWithQuestions testWithQuestions = new TestWithQuestions(test, questions);

        try {
            ServiceFactory.getInstance().getTestService().addTest(testWithQuestions);
        } catch (ServiceException e) {
            log.error("Error saving test", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher(TEST_JSP_PATH).forward(request, response);
//    }
}