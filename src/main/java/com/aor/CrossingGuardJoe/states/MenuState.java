package com.aor.CrossingGuardJoe.states;

import com.aor.CrossingGuardJoe.controller.Controller;
import com.aor.CrossingGuardJoe.controller.menu.MenuController;
import com.aor.CrossingGuardJoe.model.menu.Menu;
import com.aor.CrossingGuardJoe.viewer.Viewer;
import com.aor.CrossingGuardJoe.viewer.menu.MenuViewer;

public class MenuState extends State<Menu>{
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    public Controller<Menu> getController() {
        return new MenuController(getModel());
    }

    @Override
    public Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }
}
