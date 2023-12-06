package com.CrossingGuardJoe.ModelTest.game.elements;

import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KidTest {
    @Test
    public void testSetWalking() {
        Kid kid = new Kid(0, 0);
        kid.setWalking();
        assertTrue(kid.getWalkingState());
    }

    @Test
    public void testSetNotWalking() {
        Kid kid = new Kid(0, 0);
        kid.setWalking();
        kid.setNotWalking();
        assertFalse(kid.getWalkingState());
        assertTrue(kid.isFirstHalfOfMovement());
    }

    @Test
    public void testSetSelected() {
        Kid kid = new Kid(0, 0);
        kid.setSelected();
        assertTrue(kid.isSelected());
    }

    @Test
    public void testSetNotSelected() {
        Kid kid = new Kid(0, 0);
        kid.setSelected();
        kid.setNotSelected();
        assertFalse(kid.isSelected());
    }

    @Test
    public void testIsHit() {
        Kid kid = new Kid(0, 0);
        kid.isHit();
        assertTrue(kid.getIsHit());
        assertFalse(kid.isSelected());
    }

    @Test
    public void testSetDead() {
        Kid kid = new Kid(0, 0);
        kid.setDead();
        assertTrue(kid.getDeathCounted());
    }

    @Test
    public void testAddMovesInQueueLeft() {
        Kid kid = new Kid(0, 0);
        kid.addMovesInQueueLeft(5);
        assertEquals(5, kid.getMovesInQueueLeft());
    }

    @Test
    public void testSetPass() {
        Kid kid = new Kid(0, 0);
        kid.setPass();
        assertTrue(kid.getPass());
    }

    @Test
    public void testSetCountToNextLevel() {
        Kid kid = new Kid(0, 0);
        kid.setCountToNextLevel();
        assertTrue(kid.getCounted());
    }

    @Test
    public void testGetPoints() {
        Kid kid = new Kid(0, 0);
        assertEquals(100, kid.getPoints());
    }

    @Test
    public void testSetFirstHalfOfMovement() {
        Kid kid = new Kid(0, 0);

        assertTrue(kid.isFirstHalfOfMovement());

        kid.setFirstHalfOfMovement(false);
        assertFalse(kid.isFirstHalfOfMovement());

        kid.setFirstHalfOfMovement(true);
        assertTrue(kid.isFirstHalfOfMovement());
    }
}
