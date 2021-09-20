package com.quiz.controller.command;

import com.quiz.DB.LogConfigurator;
import com.quiz.controller.utils.Encryptor;
import com.quiz.controller.utils.Pages;
import com.quiz.controller.utils.WebPath;
import com.quiz.entity.User;
import com.quiz.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginCommand implements Command {
    private final static Logger logger;

    static {
        logger = LogConfigurator.getLogger(LoginCommand.class);
    }

    private final static String ERROR_MESSAGE = "errorMessage";

    @Override
    public WebPath execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.debug("LoginCommand starts: ");
        HttpSession session = request.getSession(true);

        logger.info("Get email: ");
        String email = request.getParameter("email");
        logger.info("email = " + email);
        String password = null;
        try {
            logger.info("Get password: ");
            password = Encryptor.encrypt(request.getParameter("password"));
            logger.info("Password successfully gotten");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Something wrong with 'hash' function");
            e.printStackTrace();
        }

        WebPath page = new WebPath(Pages.STARTPAGE, WebPath.DispatchType.FORWARD);

        if (email == null || password == null) {
            logger.error("email or/and password is/are null");
            request.setAttribute(ERROR_MESSAGE, "Email and password can't be empty");

            logger.debug("LoginCommand finished" + System.lineSeparator());
            return page;
        }
        UserService userService = new UserService();
        logger.info("Get account from DB");
        User user = userService.getUserByEmail(email);

        if (user == null) {
            logger.error("Account not found");
            request.setAttribute(ERROR_MESSAGE, "Account not found");
        } else if (!password.equals(user.getPassword())) {
            logger.error("Wrong password");
            request.setAttribute(ERROR_MESSAGE, "Wrong password");
        } else {
            logger.info("Account exists, password matches");
            session.setAttribute("user", user);
            page = new WebPath(WebPath.WebPageEnum.HOME.getPath(), WebPath.DispatchType.REDIRECT);

        }
        logger.debug("LoginCommand finished" + System.lineSeparator());
        session.setMaxInactiveInterval(10 * 60);
        return page;
    }
}