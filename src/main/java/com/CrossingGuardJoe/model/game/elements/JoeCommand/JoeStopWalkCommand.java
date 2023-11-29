package com.CrossingGuardJoe.model.game.elements.JoeCommand;

import com.CrossingGuardJoe.model.game.elements.Command;
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
