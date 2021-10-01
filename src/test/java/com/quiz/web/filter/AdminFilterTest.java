package com.quiz.web.filter;

import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.web.filters.AdminAuthorizationFilter;
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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminFilterTest {
    AdminAuthorizationFilter filter = new AdminAuthorizationFilter();

    User user = new User();
    User admin = new User();
    @Mock
    HttpSession session;

    @Mock
    FilterChain chain;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Before
    public void setUp() throws Exception{
        admin = new User.Builder()
                .setRole(Role.ADMIN)
                .build();  user = new User.Builder()
                .setRole(Role.STUDENT)
                .build();
    }
    @Test
    public void doAdminPassedFilter() throws IOException, ServletException {
        when(session.getAttribute("user")).thenReturn(admin);
        when(request.getSession(false)).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }
    @Test(expected = NullPointerException.class)
    public void doUserCantPassFilter() throws IOException, ServletException {
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getSession(false)).thenReturn(session);
        filter.doFilter(request, response, chain);
        verify(chain, times(1)).doFilter(request, response);
    }
}