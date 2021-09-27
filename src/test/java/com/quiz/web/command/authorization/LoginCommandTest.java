package com.quiz.web.command.authorization;

import com.quiz.web.command.authentication.LoginCommand;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    private LoginCommand loginCommand;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        loginCommand = new LoginCommand();
    }

    @Test
    public void getLoginPageIfAccountDoesNotExist() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("unknown@email.com");
        when(request.getParameter("password")).thenReturn("incorrectPass");
        WebPath expected = new WebPath(Pages.STARTPAGE, WebPath.DispatchType.FORWARD);
        WebPath actual = loginCommand.execute(request, response);

        assertEquals(expected, actual);
    }
    @Test
    public void getLoginPageIfPasswordIncorrect() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn("admin@mail.com");
        when(request.getParameter("password")).thenReturn("wrongPass");
        WebPath expected = new WebPath(Pages.STARTPAGE, WebPath.DispatchType.FORWARD);
        WebPath actual = loginCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}