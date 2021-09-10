package com.quiz.controller.command;

import java.util.Map;
import java.util.TreeMap;


public class CommandHolder {
    private static final Map<String, Command> commands = new TreeMap<>();

    private CommandHolder() {

    }

    static {
        commands.put("loadRegister", new LoadRegisterPageCommand());
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new ErrorCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("profile", new ProfileCommand());
        commands.put("home", new HomeCommand());
        commands.put("result", new ResultCommand());
        commands.put("editUserPersonalInfo", new EditPersonalInfoCommand());
        commands.put("loadEditPersonalInfo", new LoadEditPersonalInfoCommand());
        commands.put("listUsers", new ShowUsersCommand());
        commands.put("block", new BlockUserCommand());
        commands.put("startTest", new StartTestCommand());
        commands.put("finishQuiz", new FinishQuizCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}

