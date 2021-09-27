package com.quiz.web.command.authorization;

import com.quiz.web.command.authentication.LogoutCommand;
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
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {
    private LogoutCommand logoutCommand;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Before
    public void setUp() throws Exception {
        logoutCommand = new LogoutCommand();
    }

    @Test
    public void getLoginPageAfterLogout() throws ServletException, IOException {
        WebPath expected= new WebPath(WebPath.WebPageEnum.LOGIN.getPath(), WebPath.DispatchType.REDIRECT);
        WebPath actual = logoutCommand.execute(request, response);

        assertEquals(expected, actual);
    }

}