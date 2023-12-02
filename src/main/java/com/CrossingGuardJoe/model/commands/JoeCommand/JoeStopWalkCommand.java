package com.CrossingGuardJoe.model.commands.JoeCommand;

import com.CrossingGuardJoe.model.commands.Command;
import com.CrossingGuardJoe.model.game.elements.Joe;

public class JoeStopWalkCommand implements Command {
    private final Joe joe;

    public JoeStopWalkCommand(Joe joe) {
        this.joe = joe;
    }

    @Override
    public void execute() {
        joe.stopWalking();
    }

}