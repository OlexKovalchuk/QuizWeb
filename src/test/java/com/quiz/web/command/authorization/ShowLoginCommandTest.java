package com.quiz.web.command.authorization;

import com.quiz.web.command.authentication.ShowLoginCommand;
import com.quiz.web.utils.Pages;
import com.quiz.web.utils.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ShowLoginCommandTest {
    private ShowLoginCommand loginCommand;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        loginCommand = new ShowLoginCommand();
    }

    @Test
    public void getLoginPageAfterLogout() throws ServletException, IOException {
        WebPath expected = new WebPath(Pages.STARTPAGE, WebPath.DispatchType.FORWARD);
        WebPath actual = loginCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}