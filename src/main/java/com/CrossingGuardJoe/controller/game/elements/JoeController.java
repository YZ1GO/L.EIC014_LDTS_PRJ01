package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.controller.game.GameController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.model.game.elements.Command;
import com.CrossingGuardJoe.model.game.elements.JoeCommand.*;

public class JoeController extends GameController {
    private GUI.ACTION lastAction = GUI.ACTION.NONE;
    private Command command;

    public JoeController(Road road) {
        super(road);
    }

    private void moveJoe(int x, char direction) {
        Position newPosition = new Position(getModel().getJoe().getPosition().getX() + x,
                getModel().getJoe().getPosition().getY());

        if (canGoThrough(newPosition)) {
            JoeAction(newPosition, direction);
        } else {
            setLastActionNone();
            joeNotWalking();
        }
    }

    public void moveJoeLeft() {
        moveJoe(-6, 'l');
    }

    public void moveJoeRight() {
        moveJoe(6, 'r');
    }


    public void joePassSign() {
        JoeAction(getModel().getJoe().getPosition(), 'p');
    }

    public void joeStopSign() {
        JoeAction(getModel().getJoe().getPosition(), 's');
    }

    public void JoeAction(Position position, char passOrStop) {
        switch (passOrStop) {
            case 'p':
                this.command = new JoeRaisingPassSign(getModel().getJoe());
                break;
            case 's':
                this.command = new JoeRaisingStopSign(getModel().getJoe());
                break;
            case 'l':
                getModel().getJoe().setPosition(position);
                this.command = new JoeWalkToLeftCommand(getModel().getJoe());
                break;
            case 'r':
                getModel().getJoe().setPosition(position);
                this.command = new JoeWalkToRightCommand(getModel().getJoe());
                break;
        }

        getModel().getJoe().setAndExecuteCommand(command);
    }

    public void joeNotWalking() {
        this.command = new JoeStopWalkCommand(getModel().getJoe());
        getModel().getJoe().setAndExecuteCommand(command);
    }

    private boolean canGoThrough(Position position) {
        return (position.getX() >= 50 && position.getX() <= 420);
    }

    private void setLastActionNone() {
        this.lastAction = GUI.ACTION.NONE;
    }

    @Override
    public void nextAction(Game game, GUI.ACTION action, long time) {
        //this one, joe will stop walking if the key direction is different
        if (action == GUI.ACTION.LEFT && lastAction == GUI.ACTION.RIGHT || action == GUI.ACTION.RIGHT && lastAction == GUI.ACTION.LEFT) {
            setLastActionNone();
        }

        else if (!action.equals(lastAction) && action != GUI.ACTION.NONE) {
            lastAction = action;
        }

        if (lastAction == GUI.ACTION.LEFT) moveJoeLeft();
        if (lastAction == GUI.ACTION.RIGHT) moveJoeRight();
        if (lastAction == GUI.ACTION.UP) joeStopSign();
        if (lastAction == GUI.ACTION.DOWN) joePassSign();
        if (lastAction == GUI.ACTION.NONE) joeNotWalking();
    }
}
