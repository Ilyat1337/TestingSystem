package com.ilya.controllers;

import com.ilya.dao.Dao;
import com.ilya.dao.DaoException;
import com.ilya.dao.DaoFactory;
import com.ilya.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class TestController extends HttpServlet {
    private static final String TEST_JSP_PATH = "/WEB-INF/jsp/test/test.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String idParam = request.getParameter("id");
        //request.setAttribute("id", idParam);
        Dao<User> userDao = DaoFactory.getInstance().getUserDao();
        try {
            List<User> users = userDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        log.info("Get in test!");
        request.getRequestDispatcher(TEST_JSP_PATH).forward(request, response);

        //response.sendRedirect("index.jsp");
    }

//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher(TEST_JSP_PATH).forward(request, response);
//    }
}