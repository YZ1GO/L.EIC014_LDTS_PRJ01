package com.CrossingGuardJoe.model.commands.MenusNavigationCommand;

import com.CrossingGuardJoe.model.commands.Command;
import com.CrossingGuardJoe.model.menu.ColorPalette;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;

public class NavigateLeftCommand implements Command {
    private final Object menu;

    public NavigateLeftCommand(Object menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        if (menu instanceof CustomizeMenu customizeMenu) {
            customizeMenu.navigateLeft();
        } else if (menu instanceof ColorPalette colorPalette) {
            colorPalette.navigateColorPaletteLeft();
        } else {
            throw new IllegalArgumentException("Invalid menu type");
        }
    }
}