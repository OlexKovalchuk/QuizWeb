package com.quiz.controller.command;

import java.util.Map;
import java.util.TreeMap;


public class CommandHolder {
    private static final Map<String, Command> commandsPOST = new TreeMap<>();
    private static final Map<String, Command> commandsGET = new TreeMap<>();

    private CommandHolder() {

    }
    static {
        commandsGET.put("/register", new LoadRegisterPageCommand());
        commandsGET.put("/login", new LoadLoginCommand());
        commandsGET.put("/profile", new ProfileCommand());
        commandsGET.put("/profile/edit", new LoadEditPersonalInfoCommand());
        commandsGET.put("/home", new HomeCommand());
        commandsGET.put("/profile/results", new ResultCommand());
        commandsGET.put("/create", new CreateTestCommand());
        commandsGET.put("/finish", new ShowQuizResultCommand());
        commandsGET.put("/edit", new EditQuizCommand());
        commandsGET.put("/profile/users", new ShowUsersCommand());
        commandsGET.put("/logout", new LogoutCommand());
        commandsPOST.put("/edit", new POSTEditQuizCommand());
        commandsPOST.put("/login", new LoginCommand());
        commandsGET.put("/error", new ErrorCommand());
        commandsPOST.put("/register", new RegisterCommand());
        commandsPOST.put("/profile/edit", new EditPersonalInfoCommand());
        commandsPOST.put("/start", new StartTestCommand());
        commandsPOST.put("/finish", new FinishQuizCommand());
        commandsPOST.put("/create", new SaveQuizCommand());
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

