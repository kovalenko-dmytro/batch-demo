package com.gmail.apach.dima.batch_demo.infrastructure.input.shell.command;

import com.gmail.apach.dima.batch_demo.common.constant.ActiveProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@Profile(ActiveProfile.SHELL)
@ShellComponent
@SuppressWarnings("unused")
public class ExitCommand implements Quit.Command {

    @ShellMethod(key = {"quit", "exit", "terminate"})
    public void quit() {
        System.exit(0);
    }
}
