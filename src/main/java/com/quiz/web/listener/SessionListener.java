package com.quiz.web.listener;

import com.quiz.DB.dao.impl.RoleDAO;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;
@WebListener
public class SessionListener implements HttpSessionListener{
    public static final Logger logger =Logger.getLogger(RoleDAO.class);

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        logger.info("Session created:: ID="+sessionEvent.getSession().getId());
        sessionEvent.getSession().setAttribute("lang" ,"en");
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info("Session destroyed:: ID="+sessionEvent.getSession().getId());
    }
}