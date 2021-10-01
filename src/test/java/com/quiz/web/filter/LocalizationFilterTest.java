package com.quiz.web.filter;

import com.quiz.web.filters.LocalizationFilter;
import jakarta.servlet.FilterChain;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationFilterTest {
    LocalizationFilter filter;

    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Before
    public void setUp() throws Exception {
        filter = new LocalizationFilter();
    }

    @Test
    public void doFilterSetLanguageFromRequest() throws ServletException, IOException {
        when(request.getParameter("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(request.getSession()).setAttribute("lang", "en");
    }
}