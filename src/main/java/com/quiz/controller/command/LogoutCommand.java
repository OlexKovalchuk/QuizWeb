package com.quiz.controller.command;

import com.quiz.controller.utils.Pages;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogoutCommand implements Command {
    final static Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("LogoutCommand starts");
        HttpSession session = request.getSession(false);
        if (session != null) {
            logger.info("Get email from session:");
            logger.info("Session exists, invalidating:");
            session.invalidate();
        } else {
            logger.info("Sessions doesn't exits");
        }
        logger.debug("LogoutCommand finished" + System.lineSeparator());
        return Pages.STARTPAGE;
    }
}