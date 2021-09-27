package com.quiz.web.command.authorization;

import com.quiz.entity.Role;
import com.quiz.web.command.authentication.RegisterCommand;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    private RegisterCommand registerCommand;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    private static final String DEFAULT_PASSWORD = "PASSWORD";
    private static final String DEFAULT_NAME = "NAME";
    private static final String DEFAULT_SURNAME = "SURNAME";
    private static final String DEFAULT_MAIL = "MAIL";
    private static final long DEFAULT_ID = 0;
    private static final Role DEFAULT_ROLE = Role.STUDENT;
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";

    @Before
    public void setUp() throws Exception {
        registerCommand = new RegisterCommand();

    }

    @Test
    public void registerUserWithUsedEmail() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("MyName");
        when(request.getParameter("surname")).thenReturn("MySurname");
        when(request.getParameter("email")).thenReturn("admin@mail.com");
        when(request.getParameter("password")).thenReturn("12345678");

        WebPath expected = new WebPath(Pages.REGISTER, WebPath.DispatchType.FORWARD);
        WebPath actual = registerCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}