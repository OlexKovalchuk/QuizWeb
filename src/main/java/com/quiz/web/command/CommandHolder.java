package com.quiz.web.command;

import com.quiz.web.command.authentication.*;
import com.quiz.web.command.quiz.*;
import com.quiz.web.command.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandHolder contains all commands and methods to get special Command object by URL.
 */
public class CommandHolder {
    private static final Map<String, Command> commandsPOST = new HashMap<>();
    private static final Map<String, Command> commandsGET = new HashMap<>();

    private CommandHolder() {
    }
    static {
        commandsGET.put("/register", new ShowRegisterCommand());
        commandsGET.put("/login", new ShowLoginCommand());
        commandsGET.put("/profile", new ProfileCommand());
        commandsGET.put("/profile/edit", new ShowEditPersonalInfoCommand());
        commandsGET.put("/home", new HomeCommand());
        commandsGET.put("/profile/results", new ResultCommand());
        commandsGET.put("/create", new ShowQuizCreateCommand());
        commandsGET.put("/finish", new ShowQuizResultCommand());
        commandsGET.put("/edit", new ShowEditQuizCommand());
        commandsGET.put("/profile/users", new ShowUsersCommand());
        commandsGET.put("/logout", new LogoutCommand());
        commandsGET.put("/user/edit", new ShowUserInfoCommand());
        commandsPOST.put("/login", new LoginCommand());
        commandsGET.put("/error", new ErrorCommand());
        commandsPOST.put("/register", new RegisterCommand());
        commandsPOST.put("/profile/edit", new EditPersonalInfoCommand());
        commandsPOST.put("/start", new StartQuizCommand());
        commandsPOST.put("/finish", new FinishQuizCommand());
        commandsPOST.put("/create", new SaveQuizCommand());
        commandsPOST.put("/user/edit", new EditUserInfoCommand());
        commandsPOST.put("/delete", new DeleteQuizCommand());
        commandsPOST.put("/editQuizInfo", new EditQuizInfoCommand());
        commandsPOST.put("/user/delete", new DeleteUserCommand());
        commandsPOST.put("/editQuizQuestions", new EditQuizQuestionsCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commandsGET.containsKey(commandName)) {
            return commandsGET.get("/error");
        }
        return commandsGET.get(commandName);
    }

    public static Command getPOST(String commandName) {
        if (commandName == null || !commandsPOST.containsKey(commandName)) {
            return commandsPOST.get("/error");
        }
        return commandsPOST.get(commandName);
    }
}