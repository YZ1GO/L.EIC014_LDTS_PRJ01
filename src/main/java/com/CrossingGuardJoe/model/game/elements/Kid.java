package com.CrossingGuardJoe.model.game.elements;

public class Kid extends Element {
    private boolean walking;
    private boolean hit = false;
    private boolean selected = false;

    public Kid(int x, int y) {
        super(x, y);
    }

    public void isWalking() {
        walking = true;
    }

    public void isNotWalking() {
        walking = false;
    }

    public boolean getIsWalkingState() {
        return this.walking;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected() {
        this.selected = true;
    }

    public void setNotSelected() {
        this.selected = false;
    }

    public void isHit() { hit = true; }

    public boolean getIsHit() { return hit;}
}
