package com.quiz.DB.dao;

import com.quiz.DB.DBConnectionPool;
import com.quiz.DB.dao.impl.UserDAO;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.exceptions.UnsuccessfulQueryException;
import com.quiz.web.utils.Encryptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {
    @Mock
    private Connection connection;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        connection = DBConnectionPool.getConnection();
        userDAO = new UserDAO(connection);
    }


    @Test(expected = UnsuccessfulQueryException.class)
    public void createUserWithUsedEmailAndGetExceptions() throws UnsuccessfulQueryException, NoSuchAlgorithmException {
        User user = new User.Builder()
                .setName("name")
                .setSurname("surname")
                .setEmail("admin@mail.com")
                .setPassword(Encryptor.encrypt("12345678"))
                .setRole(Role.STUDENT)
                .setBlock(0)
                .build();
        userDAO.create(user);
    }

    @Test(expected = UnsuccessfulQueryException.class)
    public void updateUserWithUsedEmailAndGetExceptions() throws UnsuccessfulQueryException, NoSuchAlgorithmException {
        User user = new User.Builder()
                .setId(1)
                .setName("name")
                .setSurname("surname")
                .setEmail("admin@mail.com")
                .setPassword(Encryptor.encrypt("12345678"))
                .setRole(Role.STUDENT)
                .setBlock(0)
                .build();
        userDAO.create(user);
    }

    @Test
    public void updateUserWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException, NoSuchAlgorithmException {
        User user = new User.Builder()
                .setId(12342)
                .setName("name")
                .setSurname("surname")
                .setEmail("asaads")
                .setPassword(Encryptor.encrypt("12345678"))
                .setRole(Role.STUDENT)
                .setBlock(0)
                .build();
        assertFalse(userDAO.update(user));
    }

    @Test
    public void deleteUserWithWrongIdAndGetZeroChanges() throws UnsuccessfulQueryException {
        assertFalse(userDAO.delete(1234));
    }

    @After
    public void closeConnection() throws SQLException {
        connection.close();;
    }
    @After
    public void tearDown() throws Exception {
        connection.close();
    }
}