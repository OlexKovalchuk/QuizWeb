package com.quiz.controller.command;

import com.quiz.DB.dao.TestDAO;
import com.quiz.DB.dao.UserDAO;
import com.quiz.controller.utils.Encryptor;
import com.quiz.controller.utils.Pages;
import com.quiz.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginCommand implements Command {
    final static Logger logger = Logger.getLogger(LoginCommand.class);
    private final static String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("LoginCommand starts: ");
        HttpSession session = request.getSession(true);

        logger.info("Get login: ");
        String login = request.getParameter("login");
        logger.info("login = " + login);
        String password = null;
        try {
            logger.info("Get password: ");
            password = Encryptor.encrypt(request.getParameter("password"));
            logger.info("Password successfully gotten");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Something wrong with 'hash' function");
            e.printStackTrace();
        }

        String forward = Pages.STARTPAGE;
        if (login == null || password == null) {
            logger.error("email or/and password is/are null");
            request.setAttribute(ERROR_MESSAGE, "Email and password can't be empty");

            logger.debug("LoginCommand finished" + System.lineSeparator());
            return forward;
        }

        logger.info("Get account from DB");
        User user = UserDAO.getUserByLogin(login);

        if (user == null) {
            logger.error("Account not found");
            request.setAttribute(ERROR_MESSAGE, "Account not found");
        } else if (!password.equals(user.getPassword())) {
            logger.error("Wrong password");
            request.setAttribute(ERROR_MESSAGE, "Wrong password");
        } else {
            logger.info("Account exists, password matches");
            session.setAttribute("user", user);
            forward = Pages.HOME;
        }
        logger.debug("LoginCommand finished" + System.lineSeparator());
        request.setAttribute("tests", TestDAO.getAllTests());
        session.setMaxInactiveInterval(10 * 60);
        return forward;
    }
}