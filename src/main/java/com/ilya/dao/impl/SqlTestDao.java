package com.ilya.dao.impl;

import com.ilya.dao.DaoException;
import com.ilya.dao.SqlUtils;
import com.ilya.dao.connection.ConnectionProvider;
import com.ilya.dao.connection.ConnectionProviderException;
import com.ilya.dao.interfaces.TestDao;
import com.ilya.dto.TestWithQuestions;
import com.ilya.entity.Answer;
import com.ilya.entity.Question;

import java.sql.*;

public class SqlTestDao implements TestDao {
    private final ConnectionProvider connectionProvider;

    public SqlTestDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void addTest(TestWithQuestions testWithQuestions) throws DaoException {
        try (Connection connection = connectionProvider.getConnection()) {
            String insertTestCommand = "INSERT INTO test (name, creator_id) VALUES (?, ?)";
            PreparedStatement insertTestStatement = connection.prepareStatement(
                    insertTestCommand, Statement.RETURN_GENERATED_KEYS);
            insertTestStatement.setString(1, testWithQuestions.getTest().getName());
            insertTestStatement.setInt(2, testWithQuestions.getTest().getCreatorId());
            insertTestStatement.executeUpdate();

            testWithQuestions.getTest().setId(SqlUtils.getGeneratedId(insertTestStatement));

            for (Question question : testWithQuestions.getQuestions()) {
                String insertQuestionCommand = "INSERT INTO question (test_id, text, correct_answer) VALUES (?, ?, ?)";
                PreparedStatement insertQuestionStatement = connection.prepareStatement(
                        insertQuestionCommand, Statement.RETURN_GENERATED_KEYS);
                insertQuestionStatement.setInt(1, testWithQuestions.getTest().getId());
                insertQuestionStatement.setString(2, question.getText());
                insertQuestionStatement.setInt(3, question.getCorrectAnswer());
                insertQuestionStatement.executeUpdate();

                question.setId(SqlUtils.getGeneratedId(insertQuestionStatement));

                String insertAnswer = "INSERT INTO answer (question_id, text) VALUES (?, ?)";
                PreparedStatement insertAnswerStatement = connection.prepareStatement(insertAnswer);
                insertAnswerStatement.setInt(1, question.getId());
                for (Answer answer : question.getAnswers()) {
                    insertAnswerStatement.setString(2, answer.getText());
                    insertAnswerStatement.addBatch();
                }
                insertAnswerStatement.executeBatch();
            }
        } catch (ConnectionProviderException | SQLException e) {
            throw new DaoException(e);
        }
    }
}
