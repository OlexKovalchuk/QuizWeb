package com.quiz.DB.dao;

import com.quiz.DB.DBManager;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;
import com.quiz.entity.Result;
import com.quiz.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    public TestDAO() {

    }

    public static Test getTest(int id) {
        Test test = new Test();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM test where id=?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            test.setHeader(resultSet.getString("header"));
            test.setId(resultSet.getInt("id"));
            test.setDifficult(resultSet.getString("difficult"));
            test.setDuration(resultSet.getDouble("duration"));
            test.setDescription(resultSet.getString("description"));
            test.setCreateDate(resultSet.getDate("create_date"));
            test.setTopicId(resultSet.getInt("topic_id"));
            List<Question> questions = new ArrayList<>();
            try (PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM question where test_id=?")) {
                statement1.setInt(1, id);
                ResultSet resultSet1 = statement1.executeQuery();
                Question question;
                while (resultSet1.next()) {
                    List<Answer> answers;
                    question = new Question();
                    question.setDescription(resultSet1.getString("description"));
                    question.setId(resultSet1.getInt("id"));
                    try (PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM answer where " +
                            "question_id=?")) {
                        statement2.setInt(1, question.getId());
                        ResultSet resultSet2 = statement2.executeQuery();
                        answers = new ArrayList<>();
                        Answer answer;
                        while (resultSet2.next()) {
                            answer = new Answer();
                            answer.setAnswer(resultSet2.getInt("answer"));
                            answer.setDescription(resultSet2.getString("description"));
                            answers.add(answer);
                        }
                        resultSet2.close();

                    }
                    question.setAnswers(answers);
                    questions.add(question);
                }

            }
            test.setQuestions(questions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public static List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM test")) {
            ResultSet resultSet = statement.executeQuery();
            Test test;
            while (resultSet.next()) {
                test = new Test();
                test.setTopicId(resultSet.getInt("topic_id"));
                test.setDuration(resultSet.getDouble("duration"));
                test.setId(resultSet.getInt("id"));
                test.setDifficult(resultSet.getString("difficult"));
                test.setHeader(resultSet.getString("header"));
                test.setCreateDate(resultSet.getDate("create_date"));
                test.setDescription(resultSet.getString("description"));
                try (
                        PreparedStatement statement1 = connection.prepareStatement("SELECT name FROM topic where " +
                                "id=?")) {
                    statement1.setInt(1, test.getTopicId());
                    ResultSet resultSet1 = statement1.executeQuery();
                    resultSet1.next();
                    test.setTopicName(resultSet1.getString("name"));
                    resultSet1.close();
                }
                try (
                        PreparedStatement statement2 = connection.prepareStatement("SELECT COUNT(*) FROM question " +
                                "where test_id=?")) {
                    statement2.setInt(1, test.getId());
                    ResultSet resultSet1 = statement2.executeQuery();
                    resultSet1.next();
                    test.setCount(resultSet1.getInt(1));
                    resultSet1.close();
                }
                tests.add(test);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public static void updateResult(int score, int userId, int testId,int duration) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE result SET score=?, complete_date=NOW() " +
                     "where user_id=? AND test_id=? AND start_date>?")) {
            statement.setInt(1, score);
            statement.setInt(2, userId);
            statement.setInt(3, testId);
            statement.setDate(4, new Date(System.currentTimeMillis()- 1000L *60*duration));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertResult(Result result) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into result(user_id,test_id," +
                     "start_date,score,complete_date) values (?,?,NOW(),?,NOW())")) {
            statement.setInt(1, result.getUserId());
            statement.setInt(2, result.getTestId());
            statement.setInt(3, result.getScore());
            statement.executeUpdate();
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }

    public static void insertTest(Test test) throws SQLException {

        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into test(description,difficult," +
                     "duration,header,create_date,topic_id" +
                     ") values(?,?,?,?,?,?)")
        ) {
            connection.setAutoCommit(false);
            statement.setString(1, test.getDescription());
            statement.setString(2, test.getDifficult());
            statement.setDouble(3, test.getDuration());
            statement.setString(4, test.getHeader());
            statement.setInt(5, test.getTopicId());
            statement.executeUpdate();
            try (PreparedStatement statement1 = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM test;");
                 ResultSet resultSet = statement1.executeQuery()) {
                resultSet.next();
                test.setId(resultSet.getInt(1));
            }
            try (PreparedStatement statement2 = connection.prepareStatement("INSERT INTO question (test_id, " +
                    "description) values (?,?)")) {
                for (Question question : test.getQuestions()) {
                    statement2.setInt(1, test.getId());
                    statement2.setString(2, question.getDescription());
                    statement2.addBatch();
                    statement2.executeBatch();
                    try (PreparedStatement statement3 = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM" +
                            " " +
                            "question;"); ResultSet resultSet = statement3.executeQuery()) {
                        resultSet.next();
                        question.setId(resultSet.getInt(1));
                    }
                }
            }
            try (PreparedStatement statement3 = connection.prepareStatement("INSERT INTO answer (question_id, " +
                    "answer," +
                    "description) values (?,?,?)")) {
                for (Question question : test.getQuestions()) {
                    for (Answer answer : question.getAnswers()) {
                        statement3.setInt(1, question.getId());
                        statement3.setInt(2, answer.getAnswer());
                        statement3.setString(3, answer.getDescription());
                        statement3.addBatch();
                    }
                }
                statement3.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            DBManager.getConnection().rollback();
        }
    }

    public static void deleteQuestion(int id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM  question WHERE id=?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTest(int id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM  test WHERE id=?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertQuestion(Question question, int testId) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("Insert Into question( test_id, " +
                     "description) " +
                     "values (?,?)")) {
            statement.setInt(1, testId);
            statement.setString(2, question.getDescription());
            statement.execute();
            try (PreparedStatement statement3 = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM " +
                    "question;"); ResultSet resultSet = statement3.executeQuery()) {
                resultSet.next();
                question.setId(resultSet.getInt(1));
            }
            try (PreparedStatement statement3 = connection.prepareStatement("INSERT INTO answer (question_id, " +
                    "answer," +
                    "description) values (?,?,?)")) {
                for (Answer answer : question.getAnswers()) {
                    statement3.setInt(1, question.getId());
                    statement3.setInt(2, answer.getAnswer());
                    statement3.setString(3, answer.getDescription());
                    statement3.addBatch();
                }
                statement3.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}