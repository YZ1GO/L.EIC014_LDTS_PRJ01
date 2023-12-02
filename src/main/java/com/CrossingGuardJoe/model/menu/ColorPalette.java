package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.model.commands.Command;
import com.CrossingGuardJoe.viewer.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorPalette {
    private final List<Color> colorPalette;
    private int selectedColorIndex;
    private Command currentCommand;

    public ColorPalette() {
        this.colorPalette = new ArrayList<>(Arrays.asList(Color.values()));
        this.selectedColorIndex = 0;
    }

    public void setAndExecuteCommand(Command command) {
        this.currentCommand = command;

        if (this.currentCommand != null) {
            currentCommand.execute();
        }
    }

    public List<Color> getColorPalette() {
        return colorPalette;
    }

    public void navigateColorPaletteLeft() {
        selectedColorIndex = (selectedColorIndex - 1 + colorPalette.size()) % colorPalette.size();
    }

    public void navigateColorPaletteRight() {
        selectedColorIndex = (selectedColorIndex + 1) % colorPalette.size();
    }

    public Color getSelectedColor() {
        return colorPalette.get(selectedColorIndex);
    }

    public boolean isColorSelected(int index) {
        return this.selectedColorIndex == index;
    }

    public int getSelectedColorIndex() {
        return selectedColorIndex;
    }

    public void resetSelectedColorIndex() {
        this.selectedColorIndex = 0;
    }
}