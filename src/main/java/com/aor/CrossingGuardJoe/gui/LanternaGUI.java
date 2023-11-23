package com.aor.CrossingGuardJoe.gui;


import com.aor.CrossingGuardJoe.model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

public class LanternaGUI implements GUI{
    private TextGraphics graphics;
    private Screen screen;
    private Terminal terminal;
    private int width;
    private int height;
    
    public LanternaGUI(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        createTerminal();
    }

    public TextGraphics getGraphics() {
        return this.graphics;
    }

    public Screen getScreen() { return this.screen; }

    public Terminal getTerminal() { return this.terminal; }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    private void createTerminal() throws IOException {
        TerminalSize terminalSize = new TerminalSize(getWidth(), getHeight());
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setTerminalEmulatorFontConfiguration(
                        SwingTerminalFontConfiguration.newInstance(new Font("Monospaced", Font.PLAIN, 1)))
                .createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.screen.startScreen();
        this.graphics = screen.newTextGraphics();

        setBackgrounColor("#7F7976");
        refreshScreen();
    }

    @Override
    public void clearScreen() {
        this.graphics.setBackgroundColor(TextColor.Factory.fromString("#7f7976"));
        fillRectangle(new Position(0,0), getWidth(), getHeight(), ' ');
    }

    @Override
    public void refreshScreen() throws IOException {
        this.screen.refresh();
    }

    @Override
    public void setBackgrounColor(String colorHexCode) {
        this.graphics.setBackgroundColor(TextColor.Factory.fromString(colorHexCode));
        fillRectangle(new Position(0,0), getWidth(), getHeight(), ' ');
    }

    @Override
    public void fillRectangle(Position initialPosition, int rectangleWidth, int rectangleHeight, Character character) {
        this.graphics.fillRectangle(new TerminalPosition(initialPosition.getX(), initialPosition.getY()),
                new TerminalSize(rectangleWidth, rectangleHeight),
                character);
    }

    @Override
    public void drawJoe(Position position) {

    }

    @Override
    public void drawKid(Position position) {

    }

    @Override
    public void drawCar(Position position) {

    }

    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'z') return ACTION.PASS;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'x') return ACTION.STOP;

        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;

        return ACTION.NONE;
    }
}
