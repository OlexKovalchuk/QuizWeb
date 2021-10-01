package com.quiz.web.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    CommandFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new CommandFactory(request);
    }
    @Test
    public void defineCommandByUnknownUrlAndGetErrorCommandGetMethod() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/afafaf/fafaf");
        when(request.getMethod()).thenReturn("GET");
        assertEquals(CommandHolder.get("/error"),factory.defineCommand());
    }
    @Test
    public void defineCommandByUnknownUrlAndGetErrorCommandPOSTMethod() {
        when(request.getContextPath()).thenReturn("");
        when(request.getRequestURI()).thenReturn("/afafaf/fafaf");
        when(request.getMethod()).thenReturn("POST");
        assertEquals(CommandHolder.getPOST("/error"),factory.defineCommand());
    }

}