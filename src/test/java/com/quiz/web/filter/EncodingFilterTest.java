package com.quiz.web.filter;

import com.quiz.web.filters.EncodingFilter;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    EncodingFilter filter;

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
        filter = new EncodingFilter();
    }

    @Test
    public void doFilterSetEncodingToRequest() throws ServletException, IOException {
        when(request.getCharacterEncoding()).thenReturn("UTF-8");

        filter.doFilter(request, response, chain);
        assertEquals("UTF-8", request.getCharacterEncoding());
    }
}