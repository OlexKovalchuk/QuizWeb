package com.quiz.web.command.authorization;

import com.quiz.web.command.authentication.ShowRegisterCommand;
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

@RunWith(MockitoJUnitRunner.class)
public class ShowRegisterCommandTest {
    private ShowRegisterCommand registerCommand;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        registerCommand = new ShowRegisterCommand();
    }

    @Test
    public void getLoginPageAfterLogout() throws ServletException, IOException {
        WebPath expected = new WebPath(Pages.REGISTER, WebPath.DispatchType.FORWARD);
        WebPath actual = registerCommand.execute(request, response);

        assertEquals(expected, actual);
    }
}